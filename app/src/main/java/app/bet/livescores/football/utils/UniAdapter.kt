package app.bet.livescores.football.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.bet.livescores.football.data.model.DataBets

class UniAdapter(
    private val listener: UniViewHolder.OnClickListener
) : RecyclerView.Adapter<UniViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UniViewHolder.create(inflater, parent, listener)
    }

    private val items = mutableListOf<DataBets>()
    private var imageView: Int? = null
    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: UniViewHolder, position: Int) {
        holder.bind(items[position], imageView!!)

    }

    fun swapBets(bets: List<DataBets>, image: Int) {
        items.clear()
        items.addAll(bets)
        imageView = image
    }
}