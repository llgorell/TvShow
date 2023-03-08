package ir.net_box.tvshowvideo.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthTokenImpl @Inject constructor(private val app : Context) : AuthToken {

   private val Context.dataStore by preferencesDataStore(name = "my_token")
    override suspend fun saveToken(token: String) {
        app.dataStore.edit {
                preferences ->
            preferences[KEY_TOKEN] = token
        }
    }

    override suspend fun getToken(): String? {
        val preferences = app.dataStore.data.first()
        return preferences[KEY_TOKEN]
    }
    private val KEY_TOKEN = stringPreferencesKey("token")
}