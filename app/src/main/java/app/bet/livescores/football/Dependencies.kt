package app.bet.livescores.football

import android.app.Application
import android.content.Context
import app.bet.livescores.football.Dependencies.Companion.repository
import app.bet.livescores.football.Dependencies.Companion.schedulers
import app.bet.livescores.football.data.Repository
import java.lang.IllegalStateException

data class Dependencies(
    val repository: Repository,
    val schedulers: Schedulers
) {

    interface RepositoryProvider {
        fun getRepository(): Repository
    }

    interface SchedulersProvider {
        fun getShedulers(): Schedulers
    }

    companion object {

        fun schedulers(context: Context): Schedulers {
            val application = context.applicationContext as Application
            if (application is SchedulersProvider) {
                return (application as SchedulersProvider).getShedulers()
            }
            throw IllegalStateException("Application class should implement SchedulersProvider interface")
        }

        fun repository(context: Context): Repository {
            val application = context.applicationContext as Application
            if (application is RepositoryProvider) {
                return (application as RepositoryProvider).getRepository()
            }
            throw IllegalStateException("Application class should implement RepositoryProvider interface")
        }
    }
}

fun Context.dependencies() = Dependencies(
    repository(this),
    schedulers(this)
)