package com.spacedelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.spacedelivery.R
import com.spacedelivery.database.PlanetDB


abstract class FavoriteRecyclerViewAdapter(
    private var context: Context,
    private var favoriteList: List<PlanetDB>?
) : RecyclerView.Adapter<FavoriteRecyclerViewAdapter.FavoriteViewHolder>() {

    abstract fun onClickFavorite(planetModel: PlanetDB, favoriteText: String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return favoriteList!!.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.tvPlanetName.text =
            favoriteList!![position]!!.name
        holder.tvEusTime.text =  String.format("%.2f", favoriteList!![position].eus) + " EUS"

        if (favoriteList!![position].favorite == "true") {
            holder.imFavorite.background =
                ContextCompat.getDrawable(context, R.drawable.ic_full_fav)
        } else {
            holder.imFavorite.background =
                ContextCompat.getDrawable(context, R.drawable.ic_empty_fav)
        }

        holder.imFavorite.setOnClickListener {
            var favoriteText = ""
            if (favoriteList!![position].favorite == "true") {
                holder.imFavorite.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_empty_fav)
                favoriteText = "false"
            } else {
                holder.imFavorite.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_full_fav)
                favoriteText = "true"
            }
            onClickFavorite(favoriteList!![position], favoriteText)
        }
    }

    class FavoriteViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var imFavorite: ImageView = itemView.findViewById(R.id.imgFavorite)
        var tvPlanetName: TextView = itemView.findViewById(R.id.tvPlanetName)
        var tvEusTime: TextView = itemView.findViewById(R.id.tvEusTime)
    }
}