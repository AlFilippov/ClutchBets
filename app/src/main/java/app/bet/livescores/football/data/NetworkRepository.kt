package app.bet.livescores.football.data

import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.data.model.IpData
import io.reactivex.Single

interface NetworkRepository {
    fun getBets(
        apiKey: String,
        sport: String,
        region: String,
        mkt: String
    ): Single<FootballHockey>

    fun getDataWrap(): Single<String>
    fun getIpData(url:String):Single<IpData>
}