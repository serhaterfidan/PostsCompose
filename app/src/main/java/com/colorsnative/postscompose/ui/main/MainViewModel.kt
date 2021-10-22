
package com.colorsnative.postscompose.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colorsnative.postscompose.models.network.NetworkState
import com.colorsnative.postscompose.models.network.PostsResponse
import com.colorsnative.postscompose.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel() {

  private val _postsLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val postsLoadingState: State<NetworkState> get() = _postsLoadingState

  val posts: State<MutableList<PostsResponse>> = mutableStateOf(mutableListOf())
  val postsPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val newPostsFlow = postsPageStateFlow.flatMapLatest {
    _postsLoadingState.value = NetworkState.LOADING
    postsRepository.getPosts(
      success = {
        _postsLoadingState.value = NetworkState.SUCCESS
                },
      error = {
        _postsLoadingState.value = NetworkState.ERROR
      }
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  init {
    viewModelScope.launch(Dispatchers.IO) {
      newPostsFlow.collectLatest {
        posts.value.addAll(it)
      }
    }

  }

  fun getNextPosts() {
    if (postsLoadingState.value != NetworkState.LOADING) {
      postsPageStateFlow.value++
    }
  }
}
