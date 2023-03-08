package ir.net_box.tvshowvideo.data.repository

import ir.net_box.tvshowvideo.data.local.VideosListingDatabase
import ir.net_box.tvshowvideo.data.mapper.toVideoDetail
import ir.net_box.tvshowvideo.data.mapper.toVideoListing
import ir.net_box.tvshowvideo.data.mapper.toVideoListingEntity
import ir.net_box.tvshowvideo.data.remote.TechnologyApi
import ir.net_box.tvshowvideo.domain.model.VideoDetail
import ir.net_box.tvshowvideo.domain.model.VideoListing
import ir.net_box.tvshowvideo.domain.repository.TechnologyRepository
import ir.net_box.tvshowvideo.presentation.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TechnologyRepositoryImpl @Inject constructor(
    private val api : TechnologyApi,
    private val db : VideosListingDatabase

    ) : TechnologyRepository {

    private val dao = db.dao

    override suspend fun getTechnologyList(fetchFromRemote: Boolean): Flow<Resource<List<VideoListing>>>

    = flow {
            emit(Resource.Loading(true))
            val localVideos = dao.getVideoListing()
            emit(Resource.Success(
                data = localVideos.map { it.toVideoListing() }
            ))

            val isDbEmpty = localVideos.isEmpty()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListing =
                try {
                    val response = api.getFoodVideoList()
                    response.videos
                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error(" couldn't load data"))
                    null
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Error(" couldn't load data"))
                    null
                }
            remoteListing?.let { videos->
                dao.clearVideoListing()
                dao.insert(
                    videos.map { it.toVideoListingEntity() }
                )
                emit(Resource.Success(
                    data = dao.getVideoListing().map { it.toVideoListing() }
                ))
            }
        emit(Resource.Loading(false))
        }.flowOn(Dispatchers.IO)


    override suspend fun getTechnologyById(id: Int): Resource<VideoDetail> {
        return  try {
            val response = api.getProductById(id).toVideoDetail()
            Resource.Success(response)

        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn`t load Video info")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn`t load Video info")
        }
    }
}