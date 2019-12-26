package app.bet.livescores.football.features.soccergermany.view

import app.bet.livescores.football.data.model.FootballHockey
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SoccerGermanyView:MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getBetsSoccerGermany(data: FootballHockey)
}