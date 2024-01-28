package com.blinkist.theme

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.ui.graphics.vector.ImageVector
import com.blinkist.theme.Icon.*

object Icons {
    val Sort = DrawableResourceIcon(R.drawable.ic_sort)
    val Close = ImageVectorIcon(Icons.Rounded.Close)
}

sealed interface Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon
    data class DrawableResourceIcon(@DrawableRes val resourceId: Int) : Icon
}
