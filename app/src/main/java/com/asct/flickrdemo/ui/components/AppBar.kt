package com.asct.flickrdemo.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asct.flickrdemo.R
import com.asct.flickrdemo.ui.theme.FlickrDemoTheme

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBack: (() -> Unit)? = null
) {
    val resources = LocalContext.current.resources

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title ?: resources.getString(R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = onBack?.let {
            {
                IconButton(onClick = it) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        modifier = Modifier.size(24.dp),
                        contentDescription = null,
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true, widthDp = 400)
@Composable
fun AppBarPreview() {
    FlickrDemoTheme {
        AppBar(onBack = {})
    }
}

