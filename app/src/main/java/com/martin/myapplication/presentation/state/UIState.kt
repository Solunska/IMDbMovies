package com.martin.myapplication.presentation.state

import com.martin.myapplication.domain.model.MovieModel

data class UIState(
    val isLoading: Boolean = false,
    val result: List<MovieModel.Result> = emptyList(),
    val error: String = "",
) {
    data class Result(
        val id: Int,
        val genreIds: List<Int>,
        val title: String,
        val overview: String,
        val popularity: Double,
        val posterPath: String,
        val releaseDate: String,
    )
}