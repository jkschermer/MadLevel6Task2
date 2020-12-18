package com.example.madlevel6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.model.MovieDetail
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.withTimeout
import okhttp3.internal.wait

class MovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setHasOptionsMenu(true)
        initView()
    }

    private fun initView() {
        val movieDetail: MovieDetail.Result? = this.arguments?.getParcelable("MOVIE")

        context?.let { Glide.with(it).load(movieDetail?.imageGetUrlBackground()) }?.into(ivMovieBackground)
        context?.let { Glide.with(it).load(movieDetail?.imageGetUrlPoster()) }?.into(ivPosterMovie)

        tvTitleMovie.text = movieDetail?.title
        tvDateMovie.text = movieDetail?.releaseDate
        tvRatingMovie.text = movieDetail?.voteAverage.toString()
        tvOverviewMovieText.text = movieDetail?.overview
    }

}