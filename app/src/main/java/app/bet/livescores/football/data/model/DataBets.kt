package app.bet.livescores.football.data.model

import com.google.gson.annotations.SerializedName

data class DataBets(
    @field:SerializedName("sport_key") val sportKey:String,
    @field:SerializedName("sport_nice") val sportNice:String,
    @field:SerializedName("teams")val nameTeams:List<String>,
    @field:SerializedName("home_team") val homeTeam:String,
    @field:SerializedName("sites") val sites:List<BetsSite>
) {
}