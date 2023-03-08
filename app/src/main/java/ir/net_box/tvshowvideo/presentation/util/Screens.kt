package ir.net_box.tvshowvideo.presentation.util

sealed class Screens(val route : String){
    object ListVideoScreen : Screens("listvideo")
    object VideoScreen : Screens("video")
}
