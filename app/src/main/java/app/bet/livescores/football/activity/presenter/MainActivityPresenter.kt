package app.bet.livescores.football.activity.presenter

import app.bet.livescores.football.BuildConfig
import app.bet.livescores.football.RxMvpPresenter
import app.bet.livescores.football.Schedulers
import app.bet.livescores.football.activity.view.MainActivityView
import app.bet.livescores.football.data.Repository
import com.uber.autodispose.autoDisposable
import moxy.InjectViewState

@InjectViewState
class MainActivityPresenter(
    private val repository: Repository,
    private val schedulers: Schedulers
) : RxMvpPresenter<MainActivityView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.getIpData(BuildConfig.END_POINT_IP_JSON)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::getIpData, Throwable::printStackTrace)

    }

    fun getDataWrapper() {
        repository.getDataWrapper()
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::getWrapData, Throwable::printStackTrace)
    }
}
