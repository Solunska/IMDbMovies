package com.martin.myapplication.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.martin.myapplication.BuildConfig.IMAGE_BASE_URL
import com.martin.myapplication.R
import com.martin.myapplication.domain.model.MovieModel
import com.martin.myapplication.presentation.state.UIState
import com.martin.myapplication.presentation.ui.theme.montserrat
import com.martin.myapplication.presentation.ui.theme.poppins
import com.martin.myapplication.presentation.viewmodel.NowPlayingMoviesViewModel
import com.martin.myapplication.presentation.viewmodel.TopMoviesViewModel
import com.slack.eithernet.ApiResult

@Composable
fun HomePage() {
    val topMoviesViewModel: TopMoviesViewModel = hiltViewModel()
    val topMoviesState = topMoviesViewModel.state.value

    val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel = hiltViewModel()
    val nowPlayingState = nowPlayingMoviesViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF242A32))
            .padding(start = 24.dp)
    ) {
        TopRatedMovies(topMoviesState)
        MovieCategories(nowPlayingState)
    }

}

@Composable
fun TopRatedMovies(state: UIState) {
    Column(Modifier.background(color = Color(0xFF242A32))) {
        Text(
            modifier = Modifier.padding(top = 42.dp, end = 26.dp),
            text = "What do you want to watch?",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.ExtraBold,
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy((-20).dp)) {
            itemsIndexed(state.result) { index, movie ->
                ShowImage(movieItem = movie, placement = index + 1)
            }
        }

    }
}

@Composable
fun ShowImage(movieItem: MovieModel.Result, placement: Int) {
    val imageUrl = IMAGE_BASE_URL + movieItem.posterPath

    AsyncImage(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(start = 12.dp)
            .size(height = 210.dp, width = 144.61.dp),
        model = imageUrl,
        contentDescription = movieItem.title,
    )
    OutlinedText(placement.toString())
}

@Composable
fun OutlinedText(placement: String) {
    Box(modifier = Modifier.absoluteOffset(x = (-150).dp, y = 150.dp)) {
        Text(
            text = placement,
            style = fill,
        )
        Text(
            text = placement,
            style = outline,
        )
    }
}

val fill: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = montserrat,
            fontSize = 96.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF242A32),
        )
    }

val outline: TextStyle
    @Composable
    get() {
        return fill.copy(
            color = Color(0xFF0296E5),
            drawStyle = Stroke(
                miter = 10f,
                width = 2f,
                join = StrokeJoin.Miter,
            )
        )
    }


var categories: MutableList<String> = mutableListOf(
    "Now playing",
    "Upcoming",
    "Top rated",
    "Popular"
)

@Composable
fun MovieCategories(state: UIState) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 32.dp, end = 24.dp)
            .fillMaxWidth(),
    ) {
        items(categories) { category ->
            ClickableText(
                onClick = {},
                text = AnnotatedString(category),
                style = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 14.sp
                ),
            )
        }
    }

    LazyVerticalGrid(
        horizontalArrangement = Arrangement.SpaceBetween,
        columns = GridCells.Adaptive(minSize = 110.dp),
        modifier = Modifier.padding(end = 24.dp)
    ) {
        items(state.result) { movie ->

            val imageUrl = IMAGE_BASE_URL + movie.posterPath
            AsyncImage(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(height = 160.dp, width = 110.dp),
                model = imageUrl,
                contentDescription = movie.title,
            )
        }
    }

}


@Preview
@Composable
fun HomePreview() {
    HomePage()
}