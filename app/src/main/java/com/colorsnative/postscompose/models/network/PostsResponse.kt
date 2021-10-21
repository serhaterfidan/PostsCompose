

package com.colorsnative.postscompose.models.network

import androidx.compose.runtime.Immutable
import com.colorsnative.postscompose.models.NetworkResponseModel

@Immutable
data class PostsResponse(
  val userId: Int,
  val id: Int,
  val title: String,
  val body: String
) : NetworkResponseModel
