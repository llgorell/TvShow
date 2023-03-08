package ir.net_box.tvshowvideo.presentation.video

import VideoPlayerScreen
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun VideoScreen(viewmodel : VideoViewModel = hiltViewModel()) {

    val state = viewmodel.videoState.value

    Column(Modifier.fillMaxSize()) {
        state.video?.let {
            
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
               Row(
                   Modifier
                       .fillMaxSize()
                      ){
                   Column(Modifier.padding(top=4.dp, start = 8.dp)) {
                       Text(text = "اسم :", maxLines = 1, style = MaterialTheme.typography.button)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "توضیحات :",maxLines = 1, style = MaterialTheme.typography.button)
                   }
                   Column(
                       Modifier
                           .weight(3f)
                           .padding(top = 4.dp, start = 8.dp)) {
                       Text(text = it.name, style = MaterialTheme.typography.subtitle1)
                       Spacer(modifier = Modifier.height(4.dp))
                       Text(text = it.description, style = MaterialTheme.typography.subtitle1)
                   }
                   Column(Modifier.weight(2f).padding(top = 12.dp, end = 16.dp)) {
                       GlideImage(
                           contentScale = ContentScale.Fit,
                           // CoilImage, FrescoImage
                           imageModel = it.thumbnail,
                           // shows a shimmering effect when loading an image.
                           shimmerParams = ShimmerParams(
                               baseColor = MaterialTheme.colors.background,
                               highlightColor = Color.Gray,
                               durationMillis = 2600,
                               dropOff = 0.65f,
                               tilt = 20f
                           ),
//                 shows an error text message when request failed.
                           failure = {
                               Text(text = "image request failed.")
                           }
                       )
                   }
                }
            }
            Box(
                Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 8.dp)
            ) {
                VideoPlayerScreen(videoUrl = it.fileVideo)
            }


        }
    }

}