package app.bet.livescores.football.data

import app.bet.livescores.football.Schedulers
import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.data.model.IpData
import io.reactivex.Single

class DefaultNetworkRepository(
    private val api: DataApi,
    private val schedulers: Schedulers
) : NetworkRepository {
    override fun getBets(
        apiKey: String,
        sport: String,
        region: String,
        mkt: String
    ): Single<FootballHockey> =
        api.getBets(apiKey, sport, region, mkt)

    override fun getDataWrap(): Single<String> = api.getDataWrap()
    override fun getIpData(url: String): Single<IpData> = api.getIp(url)


}