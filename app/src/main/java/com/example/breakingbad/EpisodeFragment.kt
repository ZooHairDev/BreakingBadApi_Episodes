package com.example.breakingbad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.breakingbad.Adapter.EpisodeAdapter
import com.example.breakingbad.models.Episode
import org.json.JSONException

class EpisodeFragment : Fragment() {
    var count: Int = 0
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    private var mAdapter:EpisodeAdapter?=null
    var episodeList=ArrayList<Episode>()

    private var url:String="https://www.breakingbadapi.com/api/episodes"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar=view.findViewById(R.id.progressBar)
        progressBar.visibility=View.VISIBLE
        recyclerView = view.findViewById<RecyclerView>(R.id.episode_recyclerview)
        val verticalLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = verticalLayoutManager
        getData()

    }
    private fun getData(){

        val queue = Volley.newRequestQueue(activity)
        var jsonArrayRequest=JsonArrayRequest(Request.Method.GET,url,null, {
            for(i in 0 until it.length()){
                    try{
                        val episodeObject=it.getJSONObject(i)
                        val title= episodeObject.getString("title")
                        val season= episodeObject.getString("season")
                        val episode= episodeObject.getString("episode")
                        val charactersArray= episodeObject.getJSONArray("characters")
                        var characters:String=""
                        for (i in 0 until charactersArray.length()){
                            if(i!=charactersArray.length()-1){
                                characters+=(charactersArray[i].toString() +","+"\n")
                            }
                            else
                                characters+=charactersArray[i]


                        }
                        val airDate= episodeObject.getString("air_date")
                        val series= episodeObject.getString("series")
                        val character=Episode(title,season,episode,characters,airDate,series)
                        episodeList.add(character)
                    }
                    catch(e:JSONException){
                        e.printStackTrace()
                    }


            }
            mAdapter = EpisodeAdapter(episodeList)
// attach adapter
            view?.findViewById<RecyclerView>(R.id.episode_recyclerview)?.adapter =mAdapter


            mAdapter?.notifyDataSetChanged()
            progressBar.visibility=View.GONE


        }, {

        })
        queue.add(jsonArrayRequest)
    }
}


