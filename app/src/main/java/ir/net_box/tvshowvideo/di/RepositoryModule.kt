package ir.net_box.tvshowvideo.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.net_box.tvshowvideo.data.local.datastore.AuthToken
import ir.net_box.tvshowvideo.data.local.datastore.AuthTokenImpl
import ir.net_box.tvshowvideo.data.repository.TechnologyRepositoryImpl
import ir.net_box.tvshowvideo.domain.repository.TechnologyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 abstract class RepositoryModule {

     @Binds
     @Singleton
     abstract fun bindTechnologyRepository(repositoryImpl: TechnologyRepositoryImpl) : TechnologyRepository

}