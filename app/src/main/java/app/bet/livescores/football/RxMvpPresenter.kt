package app.bet.livescores.football

import androidx.annotation.CallSuper
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.ObservableSubscribeProxy
import io.reactivex.Observable
import moxy.MvpPresenter
import moxy.MvpView

open class RxMvpPresenter<V : MvpView> protected constructor() : MvpPresenter<V>() {

    private val lifecycleScopeProvider: PresenterLifecycleScopeProvider =
        PresenterLifecycleScopeProvider()

    init {
        lifecycleScopeProvider.onCreate()
    }

    fun <T> Observable<T>.autoDisposable(): ObservableSubscribeProxy<T> =
        this.`as`(AutoDispose.autoDisposable(lifecycleScopeProvider))

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        lifecycleScopeProvider.onDestroy()
    }

}
