package ir.net_box.tvshowvideo.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ir.net_box.tvshowvideo.data.remote.interceptor.TokenInterceptor
import ir.net_box.tvshowvideo.presentation.util.Screens
import ir.net_box.tvshowvideo.presentation.video.VideoScreen
import ir.net_box.tvshowvideo.presentation.videolist.VideoListScreen
import ir.net_box.tvshowvideo.ui.theme.TvShowVideoTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TvShowVideoTheme {
                // A surface container using the 'background' color from the theme
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screens.ListVideoScreen.route
                        ) {
                            composable(Screens.ListVideoScreen.route) {
                                VideoListScreen(navController)
                            }
                            composable(route = Screens.VideoScreen.route + "/{id}",
                                arguments = listOf(
                                    navArgument(
                                        name = "id"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = 1
                                    }
                                )
                            ) {
                                VideoScreen()
                            }
                        }

                    }
                }
            }
        }
    }
}

