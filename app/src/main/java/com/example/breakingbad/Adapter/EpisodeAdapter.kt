package com.example.breakingbad.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.R
import com.example.breakingbad.models.Episode

class EpisodeAdapter(val episodeList: ArrayList<Episode>) : RecyclerView.Adapter<EpisodeHolder>() {
    private var mContext: Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(
            R.layout.episode_item,
            parent,
            false
        )
        mContext=parent.context
        return  EpisodeHolder(view)

    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {


        val item=episodeList[position]


        holder.name.text=item.title
        holder.name2.text=item.airDate
        holder.name3.text= "Season: " + item.season
        holder.name4.text= "Episode number in the season: " + item.episode
        holder.name5.text= "Episode belongs to: " + item.series
        holder.name6.text= "Characters in this episode: " + item.characters




    }


    override fun getItemCount(): Int {
        return episodeList.size
    }


}



class EpisodeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val name = itemView.findViewById<TextView>(R.id.episode_name)
    val name2 = itemView.findViewById<TextView>(R.id.air_date)
    val name3 = itemView.findViewById<TextView>(R.id.season)
    val name4 = itemView.findViewById<TextView>(R.id.episode)
    val name5 = itemView.findViewById<TextView>(R.id.series)
    val name6 = itemView.findViewById<TextView>(R.id.characters)




}


