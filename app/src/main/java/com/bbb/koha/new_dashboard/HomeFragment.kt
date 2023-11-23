package com.bbb.koha.new_dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bbb.koha.R
import com.bbb.koha.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter:ArrivalsAdpter
    private lateinit var popularGenres:PopularGenres
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        addListItems()
        inRv()

        return binding.root
    }

    private fun addListItems() {
        val list = ArrayList<MovieResponse>()
        list.add(MovieResponse(R.drawable.ic_img6,"The story of my marriage","Bhagat, Chetan"))
        list.add(MovieResponse(R.drawable.google_fit,"The story of my marriage","Bhagat, Chetan"))
        list.add(MovieResponse(R.drawable.ic_img6,"The story of my marriage","Bhagat, Chetan"))
        list.add(MovieResponse(R.drawable.google_fit,"The story of my marriage","Bhagat, Chetan"))
        list.add(MovieResponse(R.drawable.rectangle,"The story of my marriage","Bhagat, Chetan"))
        list.add(MovieResponse(R.drawable.ic_img6,"The story of my marriage","Bhagat, Chetan"))
        adapter = ArrivalsAdpter(list)
        popularGenres = PopularGenres(list)
    }

    private fun inRv() {
        binding.rvArrivals.adapter=adapter
        binding.rvPickPopular.adapter=popularGenres
    }


}