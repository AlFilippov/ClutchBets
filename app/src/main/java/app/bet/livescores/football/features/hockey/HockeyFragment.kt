package app.bet.livescores.football.features.hockey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.bet.livescores.football.R
import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.dependencies
import app.bet.livescores.football.features.hockey.presenter.HockeyPresenter
import app.bet.livescores.football.features.hockey.view.HockeyView
import app.bet.livescores.football.utils.UniAdapter
import app.bet.livescores.football.utils.UniViewHolder
import kotlinx.android.synthetic.main.fragment_uniform_bets.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class HockeyFragment : MvpAppCompatFragment(), HockeyView, UniViewHolder.OnClickListener {
    companion object {
        const val TAG = "Other"
        fun newInstance() = HockeyFragment()
    }

    @InjectPresenter
    lateinit var presenter: HockeyPresenter

    @ProvidePresenter
    fun providePresenter(): HockeyPresenter {
        val (repository, schedulers) = requireContext().dependencies()
        return HockeyPresenter(repository, schedulers)
    }

    private val hockeyAdapter = UniAdapter(this)
    @ProvidePresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_uniform_bets, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvFragment) {
            layoutManager = LinearLayoutManager(context)
            adapter = hockeyAdapter
        }
    }

    override fun getBetsHockey(data: FootballHockey) {
        with(hockeyAdapter) {
            swapBets(data.data,R.drawable.hockey)
            notifyDataSetChanged()
        }
    }

    override fun showNotification() {

    }

}