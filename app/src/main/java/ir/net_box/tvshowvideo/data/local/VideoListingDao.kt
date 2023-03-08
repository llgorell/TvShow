package ir.net_box.tvshowvideo.data.local

import androidx.room.*


@Dao
interface VideoListingDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videoListingEntity: List<VideoListingEntity>)


    @Query("SELECT * FROM VideoListingEntity")
    suspend fun getVideoListing(): List<VideoListingEntity>


    @Query("DELETE FROM videolistingentity")
    suspend fun clearVideoListing()
}