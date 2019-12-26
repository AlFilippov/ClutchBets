package app.bet.livescores.football.features.hockey.presenter

import android.util.Log
import app.bet.livescores.football.ConstVal
import app.bet.livescores.football.RxMvpPresenter
import app.bet.livescores.football.Schedulers
import app.bet.livescores.football.data.Repository
import app.bet.livescores.football.features.hockey.view.HockeyView
import moxy.InjectViewState

@InjectViewState
class HockeyPresenter(
    private val repository: Repository,
    private val schedulers: Schedulers
) : RxMvpPresenter<HockeyView>() {
    companion object {
        const val TAG = "HockeyPresenter"
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.getBets(ConstVal.APPKEY, "icehockey_nhl", ConstVal.REGION, ConstVal.MKT)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::getBetsHockey,::onError)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "OnError", e)
    }
}