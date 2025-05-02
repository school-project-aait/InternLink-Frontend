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
    private const val BASE_URL = "http://10.0.2.2:3000/"  // If you move to production, replace with your real backend URL
    private lateinit var apiService: ApiService
    private var authToken: String? = null

    // Initialize Retrofit client with optional token if present
    fun initialize(context: Context) {
        authToken = PreferencesManager(context).getAuthToken() // Load the token from preferences

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor()) // Interceptor to add token in headers
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // Logs request/response for debugging
            })
            .build()

        // Create Retrofit instance if not already initialized
        if (!::apiService.isInitialized) {
            apiService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    // Retrieve the apiService, throw exception if not initialized yet
    fun getApiService(): ApiService {
        if (!::apiService.isInitialized) {
            throw IllegalStateException("RetrofitClient not initialized! Call initialize() first.") // Handle with care
        }
        return apiService
    }

    // Set the auth token and initialize client again
    fun setAuthToken(token: String, context: Context) {
        authToken = token
        PreferencesManager(context).saveAuthToken(token) // Save token in preferences
        initialize(context) // Reinitialize Retrofit with new token
    }

    // Clear auth token and reset Retrofit client
    fun clearAuthToken(context: Context) {
        authToken = null
        PreferencesManager(context).clearToken() // Clear saved token
        initialize(context) // Reinitialize Retrofit client without token
    }

    // Interceptor to attach the Authorization header with the token to every request
    private class AuthInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            // Modify the request to add the Authorization header
            val requestBuilder = originalRequest.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")

            authToken?.let {
                // Only attach the token if it's available
                requestBuilder.header("Authorization", "Bearer $it")
            }

            val request = requestBuilder.build()
            return chain.proceed(request) // Continue with the request
        }
    }
}








//hintse's
//
//object RetrofitClient {
//    private const val BASE_URL = "http://10.0.2.2:3000/"
//    private lateinit var apiService: ApiService
//    private var authToken: String? = null
//
//    fun initialize(context: Context) {
//        // Load saved token if exists
//        authToken = PreferencesManager(context).getAuthToken()
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor())
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .build()
//
//        apiService = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//
//    fun getApiService(): ApiService {
//        if (!::apiService.isInitialized) {
//            throw IllegalStateException("RetrofitClient not initialized! Call initialize() first.")
//        }
//        return apiService
//    }
//
//    fun setAuthToken(token: String, context: Context) {
//        authToken = token
//        PreferencesManager(context).saveAuthToken(token)
//        // Recreate the client with new token
//        initialize(context)
//    }
//
//    fun clearAuthToken(context: Context) {
//        authToken = null
//        PreferencesManager(context).clearToken()
//    }
//
//    private class AuthInterceptor : Interceptor {
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val originalRequest = chain.request()
//
//            val requestBuilder = originalRequest.newBuilder()
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//
//            authToken?.let {
//                requestBuilder.header("Authorization", "Bearer $it")
//            }
//
//            val request = requestBuilder.build()
//            return chain.proceed(request)
//        }
//    }
//}