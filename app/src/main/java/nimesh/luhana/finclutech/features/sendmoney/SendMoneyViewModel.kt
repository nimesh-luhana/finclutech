package nimesh.luhana.finclutech.features.sendmoney

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nimesh.luhana.finclutech.data.model.Field
import nimesh.luhana.finclutech.data.model.Provider
import nimesh.luhana.finclutech.data.model.SendMoneyRequest
import nimesh.luhana.finclutech.data.model.Service
import nimesh.luhana.finclutech.data.repository.DataRepository
import nimesh.luhana.finclutech.data.repository.RequestRepository
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel @Inject constructor(
    private val dataRepo: DataRepository,
    private val requestRepo: RequestRepository
) : ViewModel() {
    // StateFlow for service selection
    private val _selectedService = MutableStateFlow<Service?>(null)
    val selectedService: StateFlow<Service?> = _selectedService.asStateFlow()

    private val _selectedProvider = MutableStateFlow<Provider?>(null)
    val selectedProvider: StateFlow<Provider?> = _selectedProvider.asStateFlow()

    // StateFlow for provider selection
    private val _selectedProviderField = MutableStateFlow<Provider?>(null)
    val selectedProviderField: StateFlow<Provider?> = _selectedProviderField.asStateFlow()

    // StateFlow for dropdown visibility
    private val _showServicesDropdown = MutableStateFlow(true)
    val showServicesDropdown: StateFlow<Boolean> = _showServicesDropdown.asStateFlow()

    private val _showProvidersDropdown = MutableStateFlow(true)
    val showProvidersDropdown: StateFlow<Boolean> = _showProvidersDropdown.asStateFlow()


    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services

    val formState = mutableStateMapOf<String, String>()


    init {
        loadServices()
    }
    // In SendMoneyViewModel
    private val _amount = mutableStateOf("")
    val amount: String
        get() = _amount.value

    private val _bankAccountNumber = mutableStateOf("")
    val bankAccountNumber: String
        get() = _bankAccountNumber.value

    private val _lastName = mutableStateOf("")
    val lastName: String
        get() = _lastName.value

    private val _gender = mutableStateOf("")
    val gender: String
        get() = _gender.value

    private val _showGenderDropdown = MutableStateFlow(false)
    val showGenderDropdown: StateFlow<Boolean> = _showGenderDropdown.asStateFlow()


    // Update functions
    fun updateAmount(value: String) {
        _amount.value = value
    }

    fun updateBankAccountNumber(value: String) {
        _bankAccountNumber.value = value
    }

    fun updateLastName(value: String) {
        _lastName.value = value
    }

    fun selectGender(value: String) {
        _gender.value = value
        _showGenderDropdown.value = false
    }

    fun toggleGenderDropdown(show: Boolean) {
        _showGenderDropdown.value = show
    }
    private fun loadServices() {
        viewModelScope.launch {
            _services.value = dataRepo.getServices()
        }
    }

    fun selectService(service: Service) {
        _selectedService.value = service
    }

    fun toggleServicesDropdown(expanded: Boolean) {
        _showServicesDropdown.value = expanded
    }
    fun toggleProvidersDropdown(expanded: Boolean) {
        _showProvidersDropdown.value = expanded
    }

    fun updateField(fieldName: String, value: String) {
        formState[fieldName] = value
    }


    fun saveRequest() {
        viewModelScope.launch {
            requestRepo.saveRequest(
                SendMoneyRequest(
                    id = System.currentTimeMillis().toString(),
                    service = selectedService?.value?.name ?: "",
                    provider = "Selected Provider", // Add provider selection logic
                    amount = formState["amount"]?.toDouble() ?: 0.0,
                    data = formState.toMap()
                )
            )
        }
    }
    fun validateForm(): Boolean {
        return selectedService != null &&
                amount.isNotEmpty() &&
                bankAccountNumber.isNotEmpty() &&
                lastName.isNotEmpty() &&
                gender.isNotEmpty()
    }

    fun selectProviderField(service: Provider) {
        _selectedProvider.value = service
    }
}