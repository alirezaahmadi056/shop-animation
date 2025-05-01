package info.alirezaahmadi.animatedshop.ui.screen.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
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
import info.alirezaahmadi.animatedshop.util.byLocate
import kotlinx.coroutines.launch

@Composable
fun PagerInfoProduct() {

    val tabNames = listOf(
        "توضیحات",
        "ویژگی ها",
        "نظرات",
        "محصولات مشابه",
    )

    val pagerState = rememberPagerState { tabNames.size }
    TabsIndicator(
        pagerState = pagerState,
        tabs = tabNames
    )
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Top,
        key = { tabNames[it] },
        pageSpacing = 12.dp
    ) { page ->
     /*   Text(
            text =
            "${tabNames[page]}\n\n" +
                    "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )*/
        CommentsSection()
    }

}

@Composable
private fun TabsIndicator(
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

@Composable
private fun DescriptionSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "توضیحات",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 5.dp)
        )

    }

}

@Composable
private fun FeatureSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "ویژگی ها",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 5.dp)
        )

    }

}

@Composable
private fun CommentsSection() {
    data class FakeComment(
        val name: String,
        val rating: Double,
        val comment: String
    )

    val fakeComments = listOf(
        FakeComment("سعیده زینلی", 4.5, "محصول خیلی خوبی بود، ممنون."),
        FakeComment("محمدرضا نادری", 2.5, "کیفیت پایین‌تر از انتظار بود."),
        FakeComment("مینا احمدی", 3.5, "بد نبود ولی می‌شد بهتر باشه."),
        FakeComment("حسین کریمی", 5.5, "عالی و فراتر از انتظار."),
        FakeComment("نگار سلیمانی", 4.5, "طراحی زیبا و عملکرد خوب.")
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "نظرات کاربران",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .background(Color.White)
        )
        repeat(fakeComments.size) { index ->
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = fakeComments[index].name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    )  {
                        Text(
                            text = fakeComments[index].rating.toString().byLocate(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = "",
                            tint = Color(0xffFFC700),
                        )

                    }
                }
                Text(
                    text = fakeComments[index].comment,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

        }
    }

}
