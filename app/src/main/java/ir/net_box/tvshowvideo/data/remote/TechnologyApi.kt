package ir.net_box.tvshowvideo.data.remote

import ir.net_box.tvshowvideo.data.remote.dto.TechnologyDto
import ir.net_box.tvshowvideo.data.remote.dto.Video
import retrofit2.http.*

interface TechnologyApi {


    @GET("/api/v1/atv/playlist/1/")
   suspend fun getFoodVideoList(
        @Query("page") page : String = "1",
        @Query("pageSize") pageSize : String = "10"
   ) : TechnologyDto

    @GET("/api/v1/atv/video/{id}/")
    suspend fun getProductById(
        @Path("id") id: Int,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): Video


    companion object {
        const val API_KEY = "43581c49f795564442a066b11e95bcdc7dba9ac6062178d9c2fb65acce4ba761"

        const val BASE_URL = "https://channels.net-box.ir"
    }
}