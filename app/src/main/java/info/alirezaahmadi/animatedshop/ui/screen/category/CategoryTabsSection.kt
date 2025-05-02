package info.alirezaahmadi.animatedshop.ui.screen.category

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.data.model.Category
import kotlinx.coroutines.launch

@Composable
fun CategoryTabsSection(
    pagerState: PagerState,
    categoryList: List<Category>,
) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow (
        modifier = Modifier.fillMaxWidth(),
       selectedTabIndex = pagerState.currentPage,
        containerColor =  Color(0xffFCF3EC),
        divider = {},
        indicator = {},
        edgePadding = 8.dp
    ) {
        categoryList.forEachIndexed { index, category ->
            SingleTab(
                iconId = category.icon,
                selected = index == pagerState.currentPage,
                onSelected = {
                    Log.e("1212","sa$index")
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = index, animationSpec = tween(500)
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun SingleTab(
    iconId: Int,
    selected: Boolean,
    onSelected: () -> Unit
) {
    val boarderBack by animateColorAsState(
        targetValue = if (selected) Color(0xffEF472C) else
            Color.Transparent, label = ""
    )
    Box(
        modifier = Modifier
            .padding( 5.dp)
            .size(65.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, boarderBack, RoundedCornerShape(12.dp))
            .clickable(onClick = onSelected),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(iconId),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}