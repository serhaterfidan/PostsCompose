
package com.colorsnative.postscompose.repository

import androidx.annotation.WorkerThread
import com.colorsnative.postscompose.network.service.PostsService
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import timber.log.Timber

class PostsRepository constructor(
  private val postsService: PostsService
) : Repository {

  init {
    Timber.d("Injection PostsRepository")
  }

  @WorkerThread
  fun getPosts(success: () -> Unit, error: () -> Unit) = flow {
    val response = postsService.getPosts()
    response.suspendOnSuccess {
      emit(data)
    }.onError {
      error()
    }.onException { error() }
  }.onCompletion { success() }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun getPost(page: Int) = flow {
    val response = postsService.getPost(page)
    response.suspendOnSuccess {
      emit(data)
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun getUser(id: Int) = flow {
    postsService.getAuthor(id)
      .suspendOnSuccess {
        emit(data)
      }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun getComment(id: Int) = flow {
    postsService.getComments(id)
      .suspendOnSuccess {
        emit(data)
      }
  }.flowOn(Dispatchers.IO)

}
