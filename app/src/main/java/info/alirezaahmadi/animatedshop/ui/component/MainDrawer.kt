package info.alirezaahmadi.animatedshop.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainDrawer(
    drawerState: DrawerState,
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        gesturesEnabled = true,
        drawerState = drawerState,
        drawerContent = drawerContent,
        content = content
    )
}