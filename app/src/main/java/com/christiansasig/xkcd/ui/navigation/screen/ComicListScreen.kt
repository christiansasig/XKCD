package com.christiansasig.xkcd.ui.navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.christiansasig.xkcd.ui.navigation.Destinations
import com.christiansasig.xkcd.R
import com.christiansasig.xkcd.domain.model.Comic
import com.christiansasig.xkcd.ui.theme.XkcdTheme
import com.christiansasig.xkcd.ui.viewmodel.ComicViewModel

@Composable
fun ComicListScreen(
    navController: NavController,
    viewModel: ComicViewModel = viewModel()
) {
    val list by viewModel.comicModel.observeAsState(initial = emptyList())
    val loading by viewModel.isLoading.observeAsState(initial = true)
    ListScreen(navController, list, loading)
}

@Composable
fun ListScreen(
    navController: NavController,
    news: List<Comic>,
    loading: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.comic_title)) },
            )
        }
    )
    {
        Box(contentAlignment = Alignment.Center) {
            LazyColumn {
                itemsIndexed(news) { index, item ->
                    if (index == 0) {
                        FirstRow(item, navController)
                    } else {
                        ListRow(item, navController)
                    }
                }
            }
        }
        if (loading) {
            ShowProgressBar()
        }
    }
}

@Composable
fun FirstRow(item: Comic, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Destinations.DETAIL_SCREEN}/${item.num}")
            }
    ) {
        Column {
            Text(item.safeTitle, fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                    .fillMaxWidth()
                .padding(10.dp),
                style = TextStyle(textAlign = TextAlign.Center)
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .aspectRatio(16f / 9f),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = item.img)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_placeholder)
                        }).build()
                ),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ListRow(item: Comic, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Destinations.DETAIL_SCREEN}/${item.num}")
            },
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = item.img)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_placeholder)
                        }).build()
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = item.safeTitle,
                fontSize = 18.sp
            )
        }
    }

}


@Composable
fun ShowProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    XkcdTheme {
        ListScreen(
            navController = rememberNavController(),
            news = arrayListOf(
                Comic(1, 1, "Title 1", "image"),
                Comic(2, 2, "Title 2", "image"),
                Comic(3, 3, "Title 3", "image"),
            ), true
        )
    }
}