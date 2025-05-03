package com.site7x24learn.internshipfrontend.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.site7x24learn.internshipfrontend.data.model.CategoriesResponse
import com.site7x24learn.internshipfrontend.data.model.Category
import com.site7x24learn.internshipfrontend.data.model.InternshipRequest
import com.site7x24learn.internshipfrontend.data.model.InternshipResponse
import com.site7x24learn.internshipfrontend.data.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.site7x24learn.internshipfrontend.data.network.RetrofitClient
import com.site7x24learn.internshipfrontend.data.repository.InternshipRepository
import kotlinx.coroutines.launch
// Add these imports at the top of ViewModel
import com.site7x24learn.internshipfrontend.data.repository.Result
import com.site7x24learn.internshipfrontend.data.repository.Result.Success
import com.site7x24learn.internshipfrontend.data.repository.Result.Failure

class InternshipViewModel (
    private val repository: InternshipRepository
): ViewModel() {
    var formState by mutableStateOf(FormState())
        private set
    var isFormSaved by mutableStateOf(false)
        private set
    var categories by mutableStateOf<List<Category>>(emptyList())
        private set

    // 2. Add a function to fetch categories from the backend
    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getApiService().getCategories()
                if (response.isSuccessful) {
                    categories=repository.getCategories()
//                    categories = response.body()?.data?.categories ?: emptyList()

                } else {
                    Log.e("ViewModel", "Categories error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Categories fetch failed", e)
            }
        }
    }

    fun onEvent(event: InternshipEvent) {
        when(event) {
            is InternshipEvent.FieldUpdated -> updateField(event.field, event.value)
            InternshipEvent.Save -> saveForm()
            InternshipEvent.Cancel -> resetForm()
        }
    }

    private fun updateField(field: FormField, value: String) {
        formState = when (field){
            FormField.JOB -> formState.copy(job = value)
            FormField.COMPANY -> formState.copy(company = value)
            FormField.REQUIREMENTS -> formState.copy(requirements = value)
            FormField.DEADLINE -> formState.copy(deadline = value)
            FormField.CATEGORY -> formState.copy(category = value)
        }
    }



    private fun saveForm() {
        // Create the InternshipRequest object to send to the backend
//        if (formState.category.isEmpty()){
//            Log.e("Form","Category not selected")
//            return
//        }
//        try {
//
//        }
        val internshipRequest = InternshipRequest(
            title = formState.job,
            description = formState.requirements,
            deadline = formState.deadline,
            company_name = formState.company,
            category_id = formState.category.toInt()
        )
        viewModelScope.launch {
            when (val result = repository.createInternship(internshipRequest)) {
                is Result.Success -> {
                    isFormSaved = true
                    Log.d("ViewModel", "Internship created successfully: ${result.data}")
                }
                is Result.Failure -> {
                    Log.e("ViewModel",
                        "Error creating internship: ${result.exception.message ?: "Unknown error"}"
                    )
                }
            }
        }

        // Call Retrofit API to send the data
//        RetrofitClient.getApiService().createInternship(internshipRequest)
//            .enqueue(object : Callback<InternshipResponse> {
//                override fun onResponse(call: Call<InternshipResponse>, response: Response<InternshipResponse>) {
//                    if (response.isSuccessful && response.body()?.success == true) {
//                        isFormSaved = true
//                        Log.d("InternshipViewModel", "Internship created successfully")
//                    } else {
//                        Log.e("InternshipViewModel", "Failed to create internship")
//                    }
//                }
//
//                override fun onFailure(call: Call<InternshipResponse>, t: Throwable) {
//                    Log.e("InternshipViewModel", "Error: ${t.message}")
//                }
//            })

    }

    private fun resetForm() {
        formState = FormState()
    }
}

data class FormState(
    val job: String = "",
    val company: String = "",
    val requirements: String = "",
    val deadline: String = "",
    val category: String = ""
)

sealed class InternshipEvent {
    data class FieldUpdated(val field: FormField, val value: String) : InternshipEvent()
    object Save : InternshipEvent()
    object Cancel : InternshipEvent()
}

enum class FormField {
    JOB, COMPANY, REQUIREMENTS, DEADLINE, CATEGORY
}