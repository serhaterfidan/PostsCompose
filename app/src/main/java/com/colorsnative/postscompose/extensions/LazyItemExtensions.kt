
package com.colorsnative.postscompose.extensions

import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.colorsnative.postscompose.network.Api
import kotlinx.coroutines.flow.StateFlow

inline fun <T> LazyGridScope.paging(
  items: List<T>,
  currentIndexFlow: StateFlow<Int>,
  threshold: Int = 4,
  pageSize: Int = Api.PAGING_SIZE,
  crossinline fetch: () -> Unit,
  crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) {
  itemsIndexed(items) { index, item ->

    itemContent(item)

    if ((index + threshold + 1) >= pageSize * (currentIndexFlow.value - 1)) {
      fetch()
    }
  }
}
