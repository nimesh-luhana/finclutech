package nimesh.luhana.finclutech.utils

import android.content.Context
import com.google.gson.Gson
import nimesh.luhana.finclutech.R
import nimesh.luhana.finclutech.data.model.Response
import nimesh.luhana.finclutech.data.model.Service

class JsonParser(private val context: Context) {
    fun parseServices(): List<Service> {
        val inputStream = context.resources.openRawResource(R.raw.services)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Response::class.java).services
    }
}