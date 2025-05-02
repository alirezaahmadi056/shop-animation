package info.alirezaahmadi.animatedshop.ui.screen.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
 fun TabsIndicator(
    pagerState: PagerState,
    tabs: List<String>
) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 4.dp,
        containerColor = Color.White,
        indicator = {},
        divider = {},
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        tabs.forEachIndexed { index, text ->
            SingleTab(
                name = text,
                selected = pagerState.currentPage == index
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(
                        page = index,
                        animationSpec = tween(550)
                    )
                }
            }
        }
    }
}

@Composable
private fun SingleTab(
    name: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    val boarderColor by animateColorAsState(
        targetValue = if (selected) Color(0xffEF472C)
        else Color.White,
        label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) Color(0xffEF472C)
        else Color.DarkGray,
        label = ""
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, color = boarderColor, shape = RoundedCornerShape(20.dp))
            .background(Color(0xffF8F7F7))
            .clickable(onClick = onSelected),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
        )
    }
}