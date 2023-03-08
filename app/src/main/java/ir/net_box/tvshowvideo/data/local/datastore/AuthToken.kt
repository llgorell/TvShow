package ir.net_box.tvshowvideo.data.local.datastore

interface AuthToken {

    suspend fun saveToken(token : String)
    suspend fun getToken() : String?
}