package ir.net_box.tvshowvideo.presentation.video

import ir.net_box.tvshowvideo.domain.model.VideoDetail

data class VideoState(
    val video : VideoDetail? = null,
    val isLoading : Boolean = false,
    val errorMessage : String? = null
)
