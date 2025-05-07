package com.site7x24learn.internshipfrontend.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(): ViewModel() {
    var name by mutableStateOf("")
    var gender by mutableStateOf("")
    var email by mutableStateOf("")
    var contactNumber by mutableStateOf("")
    var address by mutableStateOf("")
    var linkedinUrl by mutableStateOf("")
    var universityName by mutableStateOf("")
    var degreeProgram by mutableStateOf("")
    var graduationYear by mutableStateOf("")
}
