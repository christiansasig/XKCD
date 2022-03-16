package com.christiansasig.xkcd.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christiansasig.xkcd.ui.navigation.Destinations
import com.christiansasig.xkcd.ui.navigation.screen.DetailsScreen
import com.christiansasig.xkcd.ui.navigation.screen.ComicListScreen
import com.christiansasig.xkcd.ui.theme.XkcdTheme
import com.christiansasig.xkcd.ui.viewmodel.ComicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<ComicViewModel>()
            viewModel.onCreate()
            XkcdTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ViewMain(viewModel)
                }
            }
        }
    }
}

@Composable
fun ViewMain(viewModel: ComicViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.LIST_SCREEN,
    ) {
        composable(Destinations.LIST_SCREEN) {
            ComicListScreen(navController, viewModel)
        }
        composable("${Destinations.DETAIL_SCREEN}/{num}") {
            it.arguments?.getString("num")?.let { num ->
                viewModel.getComicByNum(num.toInt())
                DetailsScreen(navController, viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    XkcdTheme {
        ViewMain()
    }
}