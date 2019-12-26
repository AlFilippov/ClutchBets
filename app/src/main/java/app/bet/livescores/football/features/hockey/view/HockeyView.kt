package app.bet.livescores.football.features.hockey.view

import app.bet.livescores.football.data.model.FootballHockey
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface HockeyView:MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getBetsHockey(data: FootballHockey)
}