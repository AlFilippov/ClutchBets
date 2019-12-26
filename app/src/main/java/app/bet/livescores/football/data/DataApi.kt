package app.bet.livescores.football.data

import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.data.model.IpData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DataApi {

    //TODO:Запросы с параметрами
    @GET("v3/odds")
    fun getBets(
        @Query("apiKey") apiKey: String,
        @Query("sport") sport: String,
        @Query("region") region: String,
        @Query("mkt") mkt: String
    ): Single<FootballHockey>

    @GET("bets")
    fun getDataWrap(): Single<String>
    @GET
    fun getIp(@Url ip:String):Single<IpData>
}