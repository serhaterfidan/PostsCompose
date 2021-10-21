

package com.colorsnative.postscompose.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.colorsnative.postscompose.ui.theme.purple200

@Composable
fun AppBarWithArrow(
  title: String,
  pressOnBack: () -> Unit
) {
  TopAppBar(
    elevation = 6.dp,
    backgroundColor = purple200,
    modifier = Modifier.height(58.dp)
  ) {
    Row {
      Spacer(modifier = Modifier.width(10.dp))

      Image(
        imageVector = Icons.Filled.ArrowBack,
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = null,
        modifier = Modifier
          .align(Alignment.CenterVertically)
          .clickable {
            pressOnBack()
          }
      )

      Text(
        text = title,
        style = MaterialTheme.typography.h6,
        color = Color.White,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 15.dp)
      )

    }
  }
}
