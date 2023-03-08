package ir.net_box.tvshowvideo.presentation.videolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ir.net_box.tvshowvideo.presentation.util.Screens


@Composable
fun VideoListScreen(navController: NavController,viewModel: VideoListViewModel = hiltViewModel()) {
    val state = viewModel.tvShowsState.collectAsState()

    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically){
        state.value.data?.let { list->
            items(list) { video->
                ItemCard(item = video,
                onClick = {
                    navController.navigate(Screens.VideoScreen.route + "/${video.id}")
                })
            }
        }

    }
}