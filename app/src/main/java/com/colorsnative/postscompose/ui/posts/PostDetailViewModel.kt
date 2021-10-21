
package com.colorsnative.postscompose.ui.posts

import androidx.lifecycle.ViewModel
import com.colorsnative.postscompose.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
  private val postsRepository: PostsRepository
) : ViewModel() {

  private val postIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

  private val postIdSharedFlow2: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

  private val userIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

  val commentFlow = postIdSharedFlow.flatMapLatest {
    postsRepository.getComment(it)
  }

  val postFlow = postIdSharedFlow2.flatMapLatest {
    postsRepository.getPost(it)
  }

  val userFlow = userIdSharedFlow.flatMapLatest {
    postsRepository.getUser(it)
  }

  init {
    Timber.d("Injection MovieDetailViewModel")
  }

  fun getCommentsById(id: Int) = postIdSharedFlow.tryEmit(id)

  fun getPostById(id: Int) = postIdSharedFlow2.tryEmit(id)

  fun getUserById(id: Int) = userIdSharedFlow.tryEmit(id)
}
