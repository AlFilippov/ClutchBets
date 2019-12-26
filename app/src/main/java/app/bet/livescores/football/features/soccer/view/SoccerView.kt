package app.bet.livescores.football.features.soccer.view

import app.bet.livescores.football.data.model.FootballHockey
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SoccerView:MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getBetsSoccer(data: FootballHockey)
}