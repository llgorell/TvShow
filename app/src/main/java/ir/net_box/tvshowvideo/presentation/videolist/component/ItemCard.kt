package ir.net_box.tvshowvideo.presentation.videolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import ir.net_box.tvshowvideo.domain.model.VideoListing

@Composable
fun ItemCard(item: VideoListing,onClick : () -> Unit) {

    Column(   modifier = Modifier
        .width(300.dp)
        .height(300.dp),
        ) {

        Card(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .clickable { onClick() }
            ,
            elevation = 8.dp
        ) {
            GlideImage(
                contentScale = ContentScale.Inside,
                // CoilImage, FrescoImage
                imageModel = item.thumbnail,
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
        Text(text = item.name, Modifier.padding(4.dp), color = Color.Black)
    }


}