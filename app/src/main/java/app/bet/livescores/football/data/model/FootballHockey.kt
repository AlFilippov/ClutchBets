package app.bet.livescores.football.data.model

import com.google.gson.annotations.SerializedName

data class FootballHockey(
    @field:SerializedName("success") val success: Boolean,
    @field:SerializedName("data") val data: List<DataBets>
) {
}