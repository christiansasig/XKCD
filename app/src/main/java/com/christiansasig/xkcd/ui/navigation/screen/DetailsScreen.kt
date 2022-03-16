package com.christiansasig.xkcd.ui.navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.christiansasig.xkcd.R
import com.christiansasig.xkcd.domain.model.Comic
import com.christiansasig.xkcd.ui.theme.XkcdTheme
import com.christiansasig.xkcd.ui.viewmodel.ComicViewModel
import com.christiansasig.xkcd.util.DateUtil

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: ComicViewModel = viewModel()
) {
    val movie by viewModel.comic.observeAsState(initial = null)
    DetailsScreen(navController, movie)
}

@Composable
fun DetailsScreen(
    navController: NavController,
    item: Comic?,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(
                            "${item?.day} ${DateUtil.getMonthNameByNumber(item?.month?.toInt() ?: 0)} ${item?.year}",
                            maxLines = 1
                        )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {
        item?.let {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column {
                    Text(
                        text = item.safeTitle,
                        fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(textAlign = TextAlign.Center)
                    )

                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current).data(data = item.img)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.ic_placeholder)
                                    error(R.drawable.ic_placeholder)
                                }).build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    XkcdTheme {
        DetailsScreen(
            navController = rememberNavController(),
            Comic(1, 1, "Title 1", "image")
        )
    }
}