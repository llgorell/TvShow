package ir.net_box.tvshowvideo.data.mapper

import ir.net_box.tvshowvideo.data.remote.dto.Video
import ir.net_box.tvshowvideo.domain.model.VideoDetail
import ir.net_box.tvshowvideo.domain.model.VideoListing
import ir.net_box.tvshowvideo.data.local.VideoListingEntity

fun VideoListingEntity.toVideoListing(): VideoListing {
    return VideoListing(
        name = name,
        thumbnail = thumbnail,
        id = idproduct
    )
}

fun Video.toVideoListingEntity(): VideoListingEntity {
    return VideoListingEntity(
        name = name,
        thumbnail = thumbnail,
        idproduct = id
    )
}

fun Video.toVideoDetail() : VideoDetail{
    return VideoDetail(
        name = name,
        description = description,
        thumbnail = thumbnail,
        fileVideo = file_src
    )
}