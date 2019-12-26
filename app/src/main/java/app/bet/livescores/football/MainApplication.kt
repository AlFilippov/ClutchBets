package app.bet.livescores.football

import androidx.multidex.MultiDexApplication
import app.bet.livescores.football.data.Repository

class MainApplication : MultiDexApplication(), Dependencies.RepositoryProvider,
    Dependencies.SchedulersProvider {

    companion object {

        const val TAG = "MainApplication"

    }

    private lateinit var repository: Repository
    private lateinit var schedulers: Schedulers

    override fun onCreate() {
        super.onCreate()
        makeDependencies()
    }

    private fun makeDependencies(){
        schedulers = DependenciesFactory.createSchedulers()
        repository = DependenciesFactory.createRepository(this)
    }

    override fun getRepository() = repository

    override fun getShedulers() = schedulers
}