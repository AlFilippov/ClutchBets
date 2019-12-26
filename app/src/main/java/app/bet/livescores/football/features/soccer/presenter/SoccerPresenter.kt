package app.bet.livescores.football.features.soccer.presenter

import android.util.Log
import app.bet.livescores.football.ConstVal
import app.bet.livescores.football.RxMvpPresenter
import app.bet.livescores.football.Schedulers
import app.bet.livescores.football.data.Repository
import app.bet.livescores.football.features.soccer.view.SoccerView
import moxy.InjectViewState

@InjectViewState
class SoccerPresenter(
    private val repository: Repository,
    private val schedulers: Schedulers
) : RxMvpPresenter<SoccerView>() {
    companion object {
        const val TAG = "SoccerPresenter"
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.getBets(ConstVal.APPKEY, "soccer_epl", ConstVal.REGION, ConstVal.MKT)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::getBetsSoccer,::onError)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "OnError", e)
    }
}