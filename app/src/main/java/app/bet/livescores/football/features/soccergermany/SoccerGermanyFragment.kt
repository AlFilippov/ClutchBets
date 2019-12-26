package app.bet.livescores.football.features.soccergermany

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.bet.livescores.football.R
import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.dependencies
import app.bet.livescores.football.features.soccergermany.presenter.SoccerGermanyPresenter
import app.bet.livescores.football.features.soccergermany.view.SoccerGermanyView
import app.bet.livescores.football.utils.UniAdapter
import app.bet.livescores.football.utils.UniViewHolder
import kotlinx.android.synthetic.main.fragment_uniform_bets.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SoccerGermanyFragment : MvpAppCompatFragment(), SoccerGermanyView, UniViewHolder.OnClickListener {
    companion object {
        const val TAG = "VolleyFragment"
        fun newInstance() = SoccerGermanyFragment()
    }

    @InjectPresenter
    lateinit var presenter: SoccerGermanyPresenter

    @ProvidePresenter
    fun providePresenter(): SoccerGermanyPresenter {
        val (repository, schedulers) = requireContext().dependencies()
        return SoccerGermanyPresenter(repository, schedulers)
    }
    private val soccerGerAdapter = UniAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_uniform_bets, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvFragment) {
            layoutManager = LinearLayoutManager(context)
            adapter = soccerGerAdapter
        }
    }

    override fun showNotification() {

    }

    override fun getBetsSoccerGermany(data: FootballHockey) {
        with(soccerGerAdapter) {
            swapBets(data.data,R.drawable.footbal)
            notifyDataSetChanged()
        }
    }

}