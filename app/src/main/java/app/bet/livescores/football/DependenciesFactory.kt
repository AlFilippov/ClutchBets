package app.bet.livescores.football

import android.content.Context
import app.bet.livescores.football.data.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object DependenciesFactory {
    fun createSchedulers() = ApplicationSchedulers()
    fun createRepository(context: Context): Repository {
        val schedulers = createSchedulers()
        val retrofit = createRetrofit(schedulers)
        val dataApi = retrofit.create(DataApi::class.java)
        val networkRepository = createNetworkRepository(dataApi, schedulers)

        val retrofitScalars = createRetrofitScalars(schedulers)
        val dataApiScalars = retrofitScalars.create(DataApi::class.java)
        val networkRepositoryScalars = createNetworkRepository(dataApiScalars, schedulers)

        val retrofitIp = createRetrofitIp(schedulers)
        val dataApiIP = retrofitIp.create(DataApi::class.java)
        val networkRepositoryIp = createNetworkRepository(dataApiIP, schedulers)

        return DefaultRepository(networkRepository, networkRepositoryScalars, networkRepositoryIp)

    }

    private fun createRetrofit(schedulers: Schedulers) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT_BACKEND)
            .client(OkHttpHelper().createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io()))
            .build()

    private fun createRetrofitIp(schedulers: Schedulers) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT_IP)
            .client(OkHttpHelper().createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io()))
            .build()

    private fun createRetrofitScalars(schedulers: Schedulers) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT_STORE)
            .client(OkHttpHelper().createOkHttpClientString())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io()))
            .build()


    private fun createNetworkRepository(api: DataApi, schedulers: Schedulers) =
        DefaultNetworkRepository(api, schedulers)
}