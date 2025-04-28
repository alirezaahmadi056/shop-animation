package info.alirezaahmadi.animatedshop.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable

@Composable
fun VerticalAnimationVisibility(
    isShow: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = isShow,
        enter = fadeIn() + expandVertically(animationSpec = tween(1000)),
        exit = fadeOut() + shrinkVertically(animationSpec = tween(1000)),
        content = content
    )

}
