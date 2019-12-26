package app.bet.livescores.football.features.soccer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.bet.livescores.football.R
import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.dependencies
import app.bet.livescores.football.features.soccer.presenter.SoccerPresenter
import app.bet.livescores.football.features.soccer.view.SoccerView
import app.bet.livescores.football.utils.UniAdapter
import app.bet.livescores.football.utils.UniViewHolder
import kotlinx.android.synthetic.main.fragment_uniform_bets.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SoccerFragment : MvpAppCompatFragment(), SoccerView, UniViewHolder.OnClickListener {
    companion object {
        const val TAG = "SoccerFragment"
        fun newInstance() = SoccerFragment()
    }

    @InjectPresenter
    lateinit var presenter: SoccerPresenter

    @ProvidePresenter
    fun providePresenter(): SoccerPresenter {
        val (repository, schedulers) = requireContext().dependencies()
        return SoccerPresenter(repository, schedulers)
    }
    private val soccerAdapter = UniAdapter(this)
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
            adapter = soccerAdapter
        }
    }

    override fun showNotification() {

    }

    override fun getBetsSoccer(data: FootballHockey) {
        with(soccerAdapter) {
            swapBets(data.data,R.drawable.footbal)
            notifyDataSetChanged()
        }
    }

}