package com.site7x24learn.internshipfrontend.data.network



import android.content.Context
import com.site7x24learn.internshipfrontend.data.PreferencesManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000/"
    private lateinit var apiService: ApiService
    private var authToken: String? = null

    fun initialize(context: Context) {
        // Load saved token if exists
        authToken = PreferencesManager(context).getAuthToken()

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun getApiService(): ApiService {
        if (!::apiService.isInitialized) {
            throw IllegalStateException("RetrofitClient not initialized! Call initialize() first.")
        }
        return apiService
    }

    fun setAuthToken(token: String, context: Context) {
        authToken = token
        PreferencesManager(context).saveAuthToken(token)
        // Recreate the client with new token
        initialize(context)
    }

    fun clearAuthToken(context: Context) {
        authToken = null
        PreferencesManager(context).clearToken()
    }

    private class AuthInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val requestBuilder = originalRequest.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")

            authToken?.let {
                requestBuilder.header("Authorization", "Bearer $it")
            }

            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }
}