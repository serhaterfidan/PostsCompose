package com.colorsnative.postscompose.ui.main

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.colorsnative.postscompose.R
import com.colorsnative.postscompose.ui.navigation.NavScreen
import com.colorsnative.postscompose.ui.posts.PostDetailScreen
import com.colorsnative.postscompose.ui.theme.purple200
import com.colorsnative.postscompose.ui.transition.PostsTransitions

@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()

    ProvideWindowInsets {
        AnimatedNavHost(
            navController = navController,
            startDestination = NavScreen.PostsScreen.route
        ) {
            composable(
                NavScreen.PostsScreen.route,
                enterTransition = { _, _ -> PostsTransitions.enterTransition() },
                exitTransition = { _, _ -> PostsTransitions.exitTransition() },
                popEnterTransition = { _, _ -> PostsTransitions.popEnterTransition() },
                popExitTransition = { _, _ -> PostsTransitions.popExitTransition() }
            ) {

                Scaffold(
                    topBar = { MainAppBar() },

                    ) {
                    PostsScreen(
                        hiltViewModel(),
                        { postsResponse ->

                            navController.navigate(
                                "${NavScreen.PostDetail.route}/${postsResponse.id}"
                            )
                        },
                        rememberLazyListState()
                    )
                }

            }

            composable(
                route = NavScreen.PostDetail.routeWithArgument,
                arguments = listOf(
                    navArgument(NavScreen.PostDetail.argument0) { type = NavType.IntType }
                ),
                enterTransition = { _, _ -> PostsTransitions.enterTransition() },
                exitTransition = { _, _ -> PostsTransitions.exitTransition() },
                popEnterTransition = { _, _ -> PostsTransitions.popEnterTransition() },
                popExitTransition = { _, _ -> PostsTransitions.popExitTransition() }
            ) { backStackEntry ->

                val postId =
                    backStackEntry.arguments?.getInt(NavScreen.PostDetail.argument0)
                        ?: return@composable

                PostDetailScreen(postId, hiltViewModel()) {
                    navController.navigateUp()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainAppBar() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = purple200,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

