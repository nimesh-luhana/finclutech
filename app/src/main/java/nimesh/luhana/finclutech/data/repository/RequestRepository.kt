package nimesh.luhana.finclutech.data.repository

import nimesh.luhana.finclutech.data.model.SendMoneyRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface RequestRepository {
    fun saveRequest(request: SendMoneyRequest)
    fun getAllRequests(): List<SendMoneyRequest>
}

class InMemoryRequestRepository : RequestRepository {
    private val _requests = MutableStateFlow<List<SendMoneyRequest>>(emptyList())
    
    override fun saveRequest(request: SendMoneyRequest) {
        _requests.value = _requests.value + request
    }

    override fun getAllRequests(): List<SendMoneyRequest> {
        return _requests.value
    }
}