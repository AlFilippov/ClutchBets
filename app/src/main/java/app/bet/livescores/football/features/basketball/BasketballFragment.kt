package app.bet.livescores.football.features.basketball

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.bet.livescores.football.Dependencies
import app.bet.livescores.football.R
import app.bet.livescores.football.data.model.FootballHockey
import app.bet.livescores.football.features.basketball.presenter.BasketBallPresenter
import app.bet.livescores.football.features.basketball.view.BasketBallView
import app.bet.livescores.football.utils.UniAdapter
import app.bet.livescores.football.utils.UniViewHolder
import kotlinx.android.synthetic.main.fragment_uniform_bets.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class BasketballFragment : MvpAppCompatFragment(), BasketBallView, UniViewHolder.OnClickListener {
    companion object {
        const val TAG = "BasketballFragment"
        fun newInstance() = BasketballFragment()
    }

    @InjectPresenter
    lateinit var presenter: BasketBallPresenter

    @ProvidePresenter
    fun providePresenter(): BasketBallPresenter {
        val repository = Dependencies.repository(requireContext())
        val schedulers = Dependencies.schedulers(requireContext())
        return BasketBallPresenter(repository, schedulers)
    }

    private val basketballAdapter = UniAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_uniform_bets, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvFragment) {
            layoutManager = LinearLayoutManager(context)
            adapter = basketballAdapter
        }
    }


    override fun showNotification() {

    }

    override fun getBetsBasketBall(data: FootballHockey) {
        with(basketballAdapter) {
            swapBets(data.data,R.drawable.basketl)
            notifyDataSetChanged()
        }
    }


}