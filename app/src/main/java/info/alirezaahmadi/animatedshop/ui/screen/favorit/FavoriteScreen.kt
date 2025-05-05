package info.alirezaahmadi.animatedshop.ui.screen.favorit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkAdd
import androidx.compose.material.icons.rounded.BookmarkAdded
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.util.byLocateAndSeparator
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun FavoriteScreen(
    mainViewModel: MainViewModel
) {
    val favoriteItems = mainViewModel.getAllFavorite().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "علاقه مندی های من",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(15.dp)
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 2
        ) {
            favoriteItems.value.forEach {
                FavoriteItemCard(it){ mainViewModel.deletedFavoriteItem(it.id) }
            }
        }
    }

}

@Composable
private fun FavoriteItemCard(
    favoriteEntity: FavoriteEntity,
    onDeleted :()-> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xffEBEBEB)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(favoriteEntity.image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(8.dp),
            ) {
                Text(
                    text = favoriteEntity.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                val discountPrice = (favoriteEntity.price * (100 - favoriteEntity.discountPercent)) / 100
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (favoriteEntity.discountPercent > 0) {
                        Text(
                            text = favoriteEntity.price.toString().byLocateAndSeparator(),
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                    Text(
                        text = discountPrice.toInt().toString().byLocateAndSeparator(),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "تومان",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        if (favoriteEntity.discountPercent > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xffFD583D),
                                Color(0xffE32A0D)
                            )
                        )
                    )
            ) {
                Text(
                    text = "${(favoriteEntity.discountPercent).toString().byLocate()}% ",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
        Box(
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(top = 20.dp, end = 8.dp)
                .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                .clickable(onClick = onDeleted),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = Icons.Rounded.BookmarkAdded,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }

    }
}