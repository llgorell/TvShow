package ir.net_box.tvshowvideo.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.net_box.tvshowvideo.data.local.VideosListingDatabase
import ir.net_box.tvshowvideo.data.local.datastore.AuthToken
import ir.net_box.tvshowvideo.data.local.datastore.AuthTokenImpl
import ir.net_box.tvshowvideo.data.remote.TechnologyApi
import ir.net_box.tvshowvideo.data.remote.interceptor.TokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockDataBase(app: Application): VideosListingDatabase {
        return Room.databaseBuilder(
            app,
            VideosListingDatabase::class.java,
            "video.db"
        ).build()
    }
    @Singleton
    @Provides
    fun provideAuthToken(@ApplicationContext app : Context): AuthToken {
        return AuthTokenImpl(app)
    }

    @Singleton
    @Provides
    fun provideTokenInterceptor(): Interceptor {
        return TokenInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideStockApi(okHttpClient: OkHttpClient): TechnologyApi {
        return Retrofit.Builder()
            .baseUrl(TechnologyApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}