package nimesh.luhana.finclutech.features.savedrequests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nimesh.luhana.finclutech.data.model.SendMoneyRequest
import nimesh.luhana.finclutech.data.repository.RequestRepository

class SavedRequestsViewModel(
    private val repository: RequestRepository
) : ViewModel() {
    private val _requests = MutableStateFlow<List<SendMoneyRequest>>(emptyList())
    val requests: StateFlow<List<SendMoneyRequest>> = _requests

    init {
        loadRequests()
    }

    private fun loadRequests() {
        viewModelScope.launch {
            _requests.value = repository.getAllRequests()
        }
    }
}