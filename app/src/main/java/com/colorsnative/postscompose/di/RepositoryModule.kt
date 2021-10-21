
package com.colorsnative.postscompose.di

import com.colorsnative.postscompose.network.service.PostsService
import com.colorsnative.postscompose.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMovieRepository(
    movieService: PostsService
  ): PostsRepository {
    return PostsRepository(movieService)
  }

}
