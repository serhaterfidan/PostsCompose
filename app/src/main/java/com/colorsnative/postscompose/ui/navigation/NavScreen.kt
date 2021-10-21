
package com.colorsnative.postscompose.ui.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class NavScreen(val route: String) {

  object PostsScreen : NavScreen("PostsScreen")

  object PostDetail : NavScreen("PostDetail") {

    const val routeWithArgument: String = "PostDetail/{postId}"

    const val argument0: String = "postId"
  }

}
