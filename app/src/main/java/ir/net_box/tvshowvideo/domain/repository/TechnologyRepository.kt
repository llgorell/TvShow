package ir.net_box.tvshowvideo.domain.repository

import ir.net_box.tvshowvideo.presentation.util.Resource
import ir.net_box.tvshowvideo.domain.model.VideoDetail
import ir.net_box.tvshowvideo.domain.model.VideoListing
import kotlinx.coroutines.flow.Flow

interface TechnologyRepository {

    suspend fun getTechnologyList(fetchFromRemote: Boolean) : Flow<Resource<List<VideoListing>>>

    suspend fun getTechnologyById(id : Int) : Resource<VideoDetail>
}