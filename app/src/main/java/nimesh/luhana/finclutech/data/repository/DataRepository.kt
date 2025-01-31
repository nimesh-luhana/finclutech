package nimesh.luhana.finclutech.data.repository

import nimesh.luhana.finclutech.data.model.Service
import nimesh.luhana.finclutech.utils.JsonParser
import javax.inject.Inject

interface DataRepository {
    fun getServices(): List<Service>
}

class MockDataRepository  @Inject constructor(
    private val jsonParser: JsonParser
) : DataRepository {


    override fun getServices(): List<Service> {
        return jsonParser.parseServices()
    }
}