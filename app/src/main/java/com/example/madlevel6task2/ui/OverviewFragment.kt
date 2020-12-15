package com.example.madlevel6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel6task2.R
import com.example.madlevel6task2.model.MovieDetail
import com.example.madlevel6task2.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment() {
    private var movies = arrayListOf<MovieDetail.Result>()
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitBtn.setOnClickListener {
            Toast.makeText(context, "You clicked on the submit button", Toast.LENGTH_SHORT).show()
            // make an api call to get the movies with the images of the movies
            viewModel.getMovies(etInputYear.text.toString())
        }

        observeMovies()
        initView()
    }

    private fun initView() {
        movieAdapter = MovieAdapter(movies, ::onMovieClick)
        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvMovies.adapter = movieAdapter
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun onMovieClick(movieDetail: MovieDetail.Result?) {
        val fragment = OverviewFragment()
        val bundle = Bundle()
        bundle.putParcelable("MOVIE", movieDetail)
        fragment.arguments = bundle
        findNavController().navigate(R.id.action_OverviewFragment_to_movieFragment, bundle)
    }
}