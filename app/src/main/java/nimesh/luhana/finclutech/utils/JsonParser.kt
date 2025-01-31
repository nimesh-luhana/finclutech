package nimesh.luhana.finclutech.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer
import nimesh.luhana.finclutech.R
import nimesh.luhana.finclutech.data.model.Placeholder
import nimesh.luhana.finclutech.data.model.Response
import nimesh.luhana.finclutech.data.model.Service
import com.google.gson.*
import java.lang.reflect.Type

class JsonParser(private val context: Context) {



    class PlaceholderAdapter : JsonDeserializer<Placeholder>, JsonSerializer<Placeholder> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Placeholder {
            return when {
                json == null || json.isJsonNull -> Placeholder.NullValue
                json.isJsonPrimitive -> Placeholder.StringValue(json.asString)
                json.isJsonObject -> {
                    val obj = json.asJsonObject
                    Placeholder.LocalizedValue(
                        en = obj.get("en")?.asString,
                        ar = obj.get("ar")?.asString
                    )
                }
                else -> throw JsonParseException("Invalid placeholder format")
            }
        }

        override fun serialize(src: Placeholder?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
            return when (src) {
                is Placeholder.StringValue -> JsonPrimitive(src.value)
                is Placeholder.LocalizedValue -> JsonObject().apply {
                    src.en?.let { addProperty("en", it) }
                    src.ar?.let { addProperty("ar", it) }
                }
                is Placeholder.NullValue, null -> JsonNull.INSTANCE
            }
        }
    }


    fun parseServices(): List<Service> {
        val inputStream = context.resources.openRawResource(R.raw.services)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return GsonBuilder()
            .registerTypeAdapter(Placeholder::class.java, PlaceholderAdapter())
            .create().fromJson(jsonString, Response::class.java).services
    }
}