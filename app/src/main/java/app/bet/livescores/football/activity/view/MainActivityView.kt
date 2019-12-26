package app.bet.livescores.football.activity.view

import app.bet.livescores.football.data.model.IpData
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainActivityView:MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getWrapData(data:String)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getIpData(ip:IpData)
}