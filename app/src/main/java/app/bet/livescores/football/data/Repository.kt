package app.bet.livescores.football.data

import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.data.model.IpData
import io.reactivex.Single

interface Repository {
    fun getBets(
        apiKey: String,
        sport: String,
        region: String,
        mkt: String
    ): Single<FootballHockey>
    fun getDataWrapper():Single<String>
    fun getIpData(url:String):Single<IpData>
}