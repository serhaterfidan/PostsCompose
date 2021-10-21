

package com.colorsnative.postscompose.di

import android.content.Context
import coil.ImageLoader
import com.colorsnative.postscompose.network.Api
import com.colorsnative.postscompose.network.RequestInterceptor
import com.colorsnative.postscompose.network.service.PostsService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideImageLoader(
    @ApplicationContext context: Context,
    okHttpClient: OkHttpClient
  ): ImageLoader {
    return ImageLoader.Builder(context)
      .okHttpClient { okHttpClient }
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okhHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okhHttpClient)
      .baseUrl(Api.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun providePostsService(retrofit: Retrofit): PostsService {
    return retrofit.create(PostsService::class.java)
  }

}
