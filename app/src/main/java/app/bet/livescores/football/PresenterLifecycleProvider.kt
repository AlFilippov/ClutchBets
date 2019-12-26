package app.bet.livescores.football

import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class PresenterLifecycleScopeProvider :
    LifecycleScopeProvider<PresenterLifecycleScopeProvider.LifecycleEvent> {

    companion object {

        private val CORRESPONDING_EVENTS =
            CorrespondingEventsFunction<LifecycleEvent> { lifecycleEvent ->
                when (lifecycleEvent) {
                    LifecycleEvent.CREATE -> LifecycleEvent.DESTROY
                    else -> throw LifecycleEndedException("Cannot bind to presenter lifecycle after destroy.")
                }
            }
    }

    private val lifecycleSubject = BehaviorSubject.create<LifecycleEvent>()

    enum class LifecycleEvent {
        CREATE,
        DESTROY
    }

    fun onCreate() = lifecycleSubject.onNext(LifecycleEvent.CREATE)

    fun onDestroy() = lifecycleSubject.onNext(LifecycleEvent.DESTROY)

    override fun lifecycle(): Observable<LifecycleEvent> = lifecycleSubject.hide()

    override fun correspondingEvents(): CorrespondingEventsFunction<LifecycleEvent> =
        CORRESPONDING_EVENTS

    override fun peekLifecycle(): LifecycleEvent? = lifecycleSubject.value

}