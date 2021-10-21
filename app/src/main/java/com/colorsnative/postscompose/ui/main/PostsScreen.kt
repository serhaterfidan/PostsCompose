package com.colorsnative.postscompose.ui.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import com.colorsnative.postscompose.extensions.paging
import com.colorsnative.postscompose.models.network.NetworkState
import com.colorsnative.postscompose.models.network.PostsResponse
import com.colorsnative.postscompose.models.network.onLoading
import com.colorsnative.postscompose.ui.main.MainViewModel

@Composable
fun PostsScreen(
    viewModel: MainViewModel,
    selectPost: (PostsResponse) -> Unit,
    lazyListState: LazyListState
) {
    val networkState: NetworkState by viewModel.postsLoadingState
    val posts by viewModel.posts

    LazyVerticalGrid(
        cells = GridCells.Fixed(1),
        state = lazyListState,
        modifier = Modifier
            .statusBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {

        paging(
            items = posts,
            currentIndexFlow = viewModel.postsPageStateFlow,
            fetch = { viewModel.getNextPosts() }
        ) {

            Post(
                post = it,
                selectPost = selectPost
            )
        }
    }

    networkState.onLoading {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun Post(
    post: PostsResponse,
    selectPost: (PostsResponse) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                selectPost(post)
            },
        elevation = 4.dp
    ) {
        ConstraintLayout {
            val (title, body) = createRefs()

            Text(
                text = post.title.capitalize(),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = post.body.capitalize(),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .constrainAs(body) {
                        top.linkTo(title.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }

}
