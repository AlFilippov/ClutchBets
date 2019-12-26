package app.bet.livescores.football

import io.reactivex.Scheduler

interface Schedulers {
    fun io(): Scheduler

    fun computation(): Scheduler

    fun mainThread(): Scheduler
}