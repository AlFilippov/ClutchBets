package app.bet.livescores.football.features.basketball.presenter

import android.util.Log
import app.bet.livescores.football.ConstVal
import app.bet.livescores.football.RxMvpPresenter
import app.bet.livescores.football.Schedulers
import app.bet.livescores.football.data.Repository
import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.features.basketball.view.BasketBallView
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.Single
import moxy.InjectViewState
import java.util.*

@InjectViewState
class BasketBallPresenter(
    private val repository: Repository,
    private val schedulers: Schedulers
) : RxMvpPresenter<BasketBallView>() {
    companion object {
        const val TAG = "BasketBallPresenter"
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.getBets(ConstVal.APPKEY, "basketball_euroleague", ConstVal.REGION, ConstVal.MKT)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::getBetsBasketBall,::onError)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "OnError", e)
    }
}