package info.alirezaahmadi.animatedshop.ui.screen.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.R
import kotlinx.coroutines.launch

@Composable
fun SliderSection() {
    val pagerState = rememberPagerState(initialPage = 2) { 5 }
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(165.dp),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 5.dp)
        ) { page ->
            SliderItem(
                tabSize = pagerState.pageCount,
                page = page,
                scrollBack = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = page - 1,
                            animationSpec = tween(650)
                        )
                    }
                },
                scrollNext = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = page + 1,
                            animationSpec = tween(650)
                        )
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Indicator(
                tabSize = 5,
                page = pagerState.currentPage
            )
        }

    }
}

@Composable
private fun SliderItem(
    tabSize: Int,
    page: Int,
    scrollNext: () -> Unit,
    scrollBack: () -> Unit
) {
    val enableNext = remember(key1 = tabSize, key2 = page) { tabSize > page + 1 }
    val enableBack = remember(key1 = page) { page > 0 }
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(Brush.linearGradient(listOf(Color(0xff658844), Color(0xff597D3C))))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.slider_circle_back),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.shoes),
                    contentDescription = "",
                    modifier = Modifier.size(110.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "از ورزشت لذت ببر!",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "تولید و عرضه انواع کفش های\n اسپرت در همه سایز ها",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    maxLines = 2,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (enableBack) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable(onClick = scrollBack),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "",
                    modifier = Modifier.padding(vertical = 12.dp),
                    tint = Color.Black
                )
            }
        }
        if (enableNext) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable(onClick = scrollNext),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier.padding(vertical = 12.dp),
                    tint = Color.Black
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Brush.linearGradient(listOf(Color(0xffFD583D),Color(0xffE32A0D)))),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "تخفیف ویژه",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }

}


@Composable
private fun Indicator(
    tabSize: Int,
    page: Int,
) {

    repeat(tabSize) { index ->
        val selected = index == page
        val backgroundColor by animateColorAsState(
            targetValue = if (selected) Color.White else Color.LightGray, label = ""
        )
        val size by animateDpAsState(
            targetValue = if (selected) 8.dp else 6.dp, label = ""
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .size(size)
                .clip(CircleShape)
                .background(backgroundColor)
        )
    }
}

