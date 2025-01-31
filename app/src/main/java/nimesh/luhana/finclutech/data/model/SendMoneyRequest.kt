package nimesh.luhana.finclutech.data.model

data class SendMoneyRequest(
    val id: String,
    val service: String,
    val provider: String,
    val amount: Double,
    val data: Map<String, String>
)