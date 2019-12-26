package app.bet.livescores.football.features.basketball.view

import app.bet.livescores.football.data.model.FootballHockey
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BasketBallView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getBetsBasketBall(data: FootballHockey)
}