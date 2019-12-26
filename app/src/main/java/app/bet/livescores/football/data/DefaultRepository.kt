package app.bet.livescores.football.data

import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.data.model.IpData
import io.reactivex.Single

class DefaultRepository(
    private val networkRepository: NetworkRepository,
    private val networkRepositoryAgent: NetworkRepository,
    private val networkRepositoryIP: NetworkRepository
) : Repository {
    override fun getBets(
        apiKey: String,
        sport: String,
        region: String,
        mkt: String
    ): Single<FootballHockey> =
        networkRepository.getBets(apiKey, sport, region, mkt)

    override fun getDataWrapper(): Single<String> =
        networkRepositoryAgent.getDataWrap()

    override fun getIpData(url: String): Single<IpData> = networkRepositoryIP.getIpData(url)


}