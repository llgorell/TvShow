package ir.net_box.tvshowvideo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VideoListingEntity::class] , version = 5, exportSchema = false)
abstract class VideosListingDatabase : RoomDatabase() {
    abstract val dao : VideoListingDao
}