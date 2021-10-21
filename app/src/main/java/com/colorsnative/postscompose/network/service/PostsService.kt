
package com.colorsnative.postscompose.network.service

import com.colorsnative.postscompose.models.network.*
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsService {
  /**
   * [Posts](http://jsonplaceholder.typicode.com/posts)
   *
   * Get the posts.
   *
   * @return [PostsResponse] response
   */
  @GET("posts")
  suspend fun getPosts(): ApiResponse<ArrayList<PostsResponse>>

  /**
   * [Posts](http://jsonplaceholder.typicode.com/posts)
   *
   * Get the post by postId.
   *
   * @return [PostsResponse] response
   */
  @GET("posts/{postId}")
  suspend fun getPost(@Path("postId") id: Int): ApiResponse<PostsResponse>

  /**
   * [Users](http://jsonplaceholder.typicode.com/users)
   *
   * Get the user.
   *
   * @param [id] Specify the id of user id.
   *
   * @return [UserResponse] response
   */
  @GET("users/{user_id}")
  suspend fun getAuthor(@Path("user_id") id: Int): ApiResponse<UserResponse>

  /**
   * [Comments](http://jsonplaceholder.typicode.com/comments)
   *
   * Get the comment.
   *
   * @param [id] Specify the id of user id.
   *
   * @return [CommentsResponse] response
   */
  @GET("comments")
  suspend fun getComments(@Query("postId") id: Int): ApiResponse<ArrayList<CommentsResponse>>
}
