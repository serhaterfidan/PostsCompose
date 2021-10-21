

package com.colorsnative.postscompose.models.network

import androidx.compose.runtime.Immutable
import com.colorsnative.postscompose.models.NetworkResponseModel

@Immutable
data class UserResponse(
  val id: Int,
  val name: String,
  val username: String,
  val email: String
) : NetworkResponseModel
