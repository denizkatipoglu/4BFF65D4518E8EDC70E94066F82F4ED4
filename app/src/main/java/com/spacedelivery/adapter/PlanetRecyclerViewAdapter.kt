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
import com.spacedelivery.models.PlanetModel
import java.util.*


abstract class PlanetRecyclerViewAdapter(
    private var context: Context,
    private var planetList: List<PlanetDB>?
) : RecyclerView.Adapter<PlanetRecyclerViewAdapter.PlanetViewHolder>() {

    abstract fun onClickTravel(planetModel: PlanetDB)
    abstract fun onClickFavorite(planetModel: PlanetDB, favoriteText: String)

    var originalList = ArrayList<PlanetDB>()
    var temporaryList = ArrayList<PlanetDB>()

    init {
        originalList.addAll(planetList!!)
        temporaryList.addAll(planetList!!)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        return PlanetViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_planet, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return temporaryList!!.size
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.tvNeed.text =
            temporaryList[position].need.toString() + "/" + temporaryList[position].stock.toString()
        holder.tvEusTime.text =
            String.format("%.2f", temporaryList[position].eus) + " EUS"
        holder.tvPlanetName.text = temporaryList[position].name

        if (temporaryList!![position].favorite == "true") {
            holder.imFavorite.background =
                ContextCompat.getDrawable(context, R.drawable.ic_full_fav)
        } else {
            holder.imFavorite.background =
                ContextCompat.getDrawable(context, R.drawable.ic_empty_fav)
        }

        holder.imFavorite.setOnClickListener {
            var favoriteText = ""
            if (temporaryList!![position].favorite == "true") {
                holder.imFavorite.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_empty_fav)
                favoriteText = "false"
            } else {
                holder.imFavorite.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_full_fav)
                favoriteText = "true"
            }
            onClickFavorite(temporaryList[position], favoriteText)
        }


        holder.btnTravel.setOnClickListener {
            onClickTravel(temporaryList[position])
        }
    }

    fun filter(text: String) {
        var text = text
        temporaryList.clear()
        if (text.isEmpty()) {
            temporaryList.addAll(originalList)
        } else {
            text = text.toLowerCase()
            for (item in originalList) {
                if (item.name != null && item.name.toLowerCase().contains(text)) {
                    temporaryList.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }

    class PlanetViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var imFavorite: ImageView = itemView.findViewById(R.id.imgFavorite)
        var tvNeed: TextView = itemView.findViewById(R.id.tvNeed)
        var tvEusTime: TextView = itemView.findViewById(R.id.tvEusTime)
        var tvPlanetName: TextView = itemView.findViewById(R.id.planetName)
        var btnTravel: Button = itemView.findViewById(R.id.btnTravel)
    }
}