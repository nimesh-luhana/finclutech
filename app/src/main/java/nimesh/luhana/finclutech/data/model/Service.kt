    package nimesh.luhana.finclutech.data.model


    import com.google.gson.annotations.SerializedName

    data class Response(
        val title: Title,
        val services: List<Service>
    )

    data class Title(
        val en: String,
        val ar: String
    )

    data class Service(
        val name: String,
        val label: LocalizedText,
        val providers: List<Provider>
    )

    data class Provider(
        val id: String,
        val name: String,
        @SerializedName("required_fields")
        val requiredFields: List<Field>
    )

    data class Field(
        val name: String,
        val label: LocalizedText,
        val type: String,
        val validation: String,
        val placeholder: Placeholder,
        val maxLength: Int,
        val options: List<Option> = emptyList()
    ){
        fun getPlaceholder(): String {
            return when (placeholder) {
                is Placeholder.StringValue -> placeholder.value
                is Placeholder.LocalizedValue -> placeholder.en ?: placeholder.ar ?: ""
                else -> {""}
            }
        }
    }
    sealed class Placeholder {
        data class StringValue(val value: String) : Placeholder()
        data class LocalizedValue(val en: String? = null, val ar: String? = null) : Placeholder()
        object NullValue : Placeholder()
    }

    data class Option(
        val label: String,
        val name: String
    )

    data class LocalizedText(
        val en: String,
        val ar: String
    ) {
//        @Composable
//        fun currentLanguageText(): String {
//            return if (LocalAppLanguage.current.language == "ar") ar else en
//        }
    }