package ir.net_box.tvshowvideo.data.remote.dto

data class Video(
    val content_type: Int,
    val description: String,
    val duration: Int,
    val `file`: Any,
    val file_size: Int,
    val file_src: String,
    val id: Int,
    val name: String,
    val publish_date: String,
    val quality: Int,
    val thumbnail: String
)