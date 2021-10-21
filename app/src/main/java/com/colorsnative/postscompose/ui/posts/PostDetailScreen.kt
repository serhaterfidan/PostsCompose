package com.colorsnative.postscompose.ui.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.colorsnative.postscompose.R
import com.colorsnative.postscompose.models.network.CommentsResponse
import com.colorsnative.postscompose.models.network.PostsResponse
import com.colorsnative.postscompose.models.network.UserResponse
import com.colorsnative.postscompose.ui.custom.AppBarWithArrow
import com.skydoves.whatif.whatIfNotNullOrEmpty

@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: PostDetailViewModel,
    pressOnBack: () -> Unit
) {

    LaunchedEffect(key1 = postId) {
        viewModel.getPostById(postId)
    }

    LaunchedEffect(key1 = postId) {
        viewModel.getCommentsById(postId)
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {

        PostTitleWithBody(pressOnBack,viewModel)

        AuthorInfo(viewModel)

        PostsDetailReviews(viewModel)

    }

}

@Composable
private fun PostTitleWithBody(pressOnBack: () -> Unit,viewModel: PostDetailViewModel) {
    val post: PostsResponse? by viewModel.postFlow.collectAsState(initial = null)

    post?.let {
        LaunchedEffect(key1 = it.userId) {
            viewModel.getUserById(it.userId)
        }
    }

    post?.let {

        AppBarWithArrow(it.title.capitalize(),pressOnBack)

        Text(
            text = it.body.capitalize(),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Composable
private fun AuthorInfo(viewModel: PostDetailViewModel) {
    val author: UserResponse? by viewModel.userFlow.collectAsState(initial = null)

    author?.let {
        Text(
            text = it.username.capitalize(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Right,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 8.dp)
        )

        Text(
            text = it.name.capitalize() + " - " + it.email,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Right,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}

@Composable
private fun PostsDetailReviews(
    viewModel: PostDetailViewModel
) {
    val reviews: ArrayList<CommentsResponse>? by viewModel.commentFlow.collectAsState(initial = null)

    reviews.whatIfNotNullOrEmpty {

        Text(
            text = stringResource(R.string.comments),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        reviews?.forEach {

            Column {
                Review(it)
            }
        }
    }
}

@Composable
private fun Review(
    commentsResponse: CommentsResponse
) {

    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = 4.dp
    ) {
        ConstraintLayout {
            val (title, body) = createRefs()

            Text(
                text = commentsResponse.name.capitalize(),
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
                text = commentsResponse.body.capitalize(),
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
