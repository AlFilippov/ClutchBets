package app.bet.livescores.football.data

import com.google.gson.annotations.SerializedName

class ServerError (
    @SerializedName("error") val error: String?,
    @SerializedName("error_description") val errorDecription: String?
) {

    val message: String?
        get() = if (!errorDecription.isNullOrEmpty()) errorDecription else error

}