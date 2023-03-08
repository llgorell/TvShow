package ir.net_box.tvshowvideo.data.remote.dto

data class TechnologyDto(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Any,
    val videos: List<Video>,
    val videos_count: Int)

