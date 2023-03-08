package ir.net_box.tvshowvideo.presentation.videolist

import ir.net_box.tvshowvideo.domain.model.VideoListing

data class TVShowState(
    val data: List<VideoListing>? = emptyList(),
    val loading: Boolean = false,
    val errorMessage: String? = null
    )
