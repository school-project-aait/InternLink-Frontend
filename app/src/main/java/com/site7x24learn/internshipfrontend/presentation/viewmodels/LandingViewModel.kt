package com.site7x24learn.internshipfrontend.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor() : ViewModel() {
    // Single event flow for navigation
    private val _navigationEvents = Channel<String>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    fun navigateWithLoading(destination: String) {
        viewModelScope.launch {
            _navigationEvents.send(Routes.WAITING)
            delay(500)
            _navigationEvents.send(destination)
        }
    }
}