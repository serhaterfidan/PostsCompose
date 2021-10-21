

package com.colorsnative.postscompose.ui.transition

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object PostsTransitions {

  fun enterTransition(
    duration: Int = 500
  ): EnterTransition {
    return slideInHorizontally(
      initialOffsetX = { 1000 },
      animationSpec = tween(
        durationMillis = duration,
        easing = FastOutSlowInEasing
      )
    ) + fadeIn(animationSpec = tween(durationMillis = duration))
  }

  fun exitTransition(
    duration: Int = 500
  ): ExitTransition {
    return slideOutHorizontally(
      targetOffsetX = { -1000 },
      animationSpec = tween(
        durationMillis = duration,
        easing = FastOutSlowInEasing
      )
    ) + fadeOut(animationSpec = tween(durationMillis = duration))
  }

  fun popEnterTransition(
    duration: Int = 500
  ): EnterTransition {
    return slideInHorizontally(
      initialOffsetX = { -1000 },
      animationSpec = tween(
        durationMillis = duration,
        easing = FastOutSlowInEasing
      )
    ) + fadeIn(animationSpec = tween(durationMillis = duration))
  }

  fun popExitTransition(
    duration: Int = 500
  ): ExitTransition {
    return slideOutHorizontally(
      targetOffsetX = { 1000 },
      animationSpec = tween(
        durationMillis = duration,
        easing = FastOutSlowInEasing
      )
    ) + fadeOut(animationSpec = tween(durationMillis = duration))
  }
}
