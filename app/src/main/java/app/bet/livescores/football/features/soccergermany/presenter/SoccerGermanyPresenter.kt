package app.bet.livescores.football.features.soccergermany.presenter

import android.util.Log
import app.bet.livescores.football.ConstVal
import app.bet.livescores.football.RxMvpPresenter
import app.bet.livescores.football.Schedulers
import app.bet.livescores.football.data.Repository
import app.bet.livescores.football.features.soccergermany.view.SoccerGermanyView
import moxy.InjectViewState

@InjectViewState
class SoccerGermanyPresenter(
    private val repository: Repository,
    private val schedulers: Schedulers
) : RxMvpPresenter<SoccerGermanyView>() {
    companion object {
        const val TAG = "SoccerGermanyPresenter"
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.getBets(ConstVal.APPKEY, "soccer_germany_bundesliga", ConstVal.REGION, ConstVal.MKT)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::getBetsSoccerGermany,::onError)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "OnError", e)
    }
}