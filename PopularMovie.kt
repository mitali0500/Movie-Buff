package com.mitali.moviebuff.fregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitali.moviebuff.R


class PopularMovie : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_popular_movie, container, false)

        recyclerView = view!!.findViewById(R.id.recycler_view_pop_frag)
        linearLayoutManager = LinearLayoutManager(activity)

        return view
    }

}