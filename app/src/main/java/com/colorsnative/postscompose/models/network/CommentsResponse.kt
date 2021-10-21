

package com.colorsnative.postscompose.models.network

import androidx.compose.runtime.Immutable
import com.colorsnative.postscompose.models.NetworkResponseModel

@Immutable
class CommentsResponse(
  val postId: Int,
  val id: Int,
  val name: String,
  val email: String,
  val body: String
) : NetworkResponseModel
