package nimesh.luhana.finclutech.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    // Update functions
    fun updateUsername(value: String) { _username.value = value }
    fun updatePassword(value: String) { _password.value = value }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (validateCredentials()) {
                _errorMessage.value = ""
                onSuccess()
            } else {
                _errorMessage.value = "Invalid username or password"
            }
        }
    }

    private fun validateCredentials(): Boolean {
        return username.value == "testuser" && password.value == "password123"
    }
}