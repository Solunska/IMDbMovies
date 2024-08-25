package com.martin.myapplication.data.repository

import com.martin.myapplication.data.remote.api.MoviesApi
import com.martin.myapplication.data.remote.dto.MovieDTO
import com.martin.myapplication.data.remote.dto.MoviesError
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val moviesApi: MoviesApi) {

    suspend fun getTopRatedMovies(): ApiResult<MovieDTO, MoviesError> {
        val result = moviesApi.getPopularMovies()

        when (result) {
            is ApiResult.Success -> {
                val movies = result.value.results
                println("Fetched Movies: $movies")
            }

            is ApiResult.Failure -> {
                println("Error fetching movies")
            }
        }
        return result
    }

    suspend fun getNowPlayingMovies(): ApiResult<MovieDTO, MoviesError> {
        val result = moviesApi.getNowPlayingMovies()

        when (result) {
            is ApiResult.Success -> {
                val nowPlayingMovies = result.value.results
                println("Fetched Movies: $nowPlayingMovies")
            }

            is ApiResult.Failure -> {
                println("Error fetching movies")
            }
        }
        return result
    }

}