package app.bet.livescores.football.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.bet.livescores.football.R
import app.bet.livescores.football.data.model.DataBets
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_uniform_bets.view.*


class UniViewHolder(
    view: View,
    val listener: OnClickListener
) : RecyclerView.ViewHolder(view), LayoutContainer {
    interface OnClickListener {
        fun showNotification()
    }

    companion object {
        fun create(
            inflater: LayoutInflater,
            parent: ViewGroup,
            listener: OnClickListener
        ): UniViewHolder {
            val itemView = inflater.inflate(R.layout.item_uniform_bets, parent, false)
            return UniViewHolder(itemView, listener)
        }
    }

    override val containerView: View?
        get() = itemView

    fun bind(bets: DataBets, imageView: Int) {

        itemView.titleSports.text = bets.sportNice
        itemView.teamSports.text = bets.nameTeams[0]
        cropGlide(itemView.imageSports, imageView)
        itemView.scoreSports.text = getFirstList(bets, 0, 0)
        itemView.teamSportsOne.text = bets.nameTeams[1]
        cropGlide(itemView.imageSportsOne, imageView)
        itemView.scoreSportsOne.text = getFirstList(bets, 0, 1)
        itemView.homeTeam.text = "Playing at home:${bets.homeTeam}"
    }

    private fun getFirstList(bets: DataBets, indexSites: Int, indexH2: Int): String {
        return if (bets.sites.isEmpty()) {
            "2"
        } else {
            bets.sites[indexSites].odds.h2h[indexH2].toString()
        }
    }

    private fun cropGlide(image: ImageView, path: Int) {

        Glide.with(itemView.context)
            .load(path)
            .apply(RequestOptions.bitmapTransform( RoundedCorners(30)))
            .into(image)
    }
}