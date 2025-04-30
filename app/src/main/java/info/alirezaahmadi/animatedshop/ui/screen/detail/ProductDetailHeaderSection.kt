package info.alirezaahmadi.animatedshop.ui.screen.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.data.model.Product
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun ProductDetailHeaderSection(
    modifier: Modifier,
    product: Product,
    mainViewModel: MainViewModel,
) {
    val isHaveFavorite by mainViewModel.isFavorite(itemId = product.id).collectAsState(false)
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "",
                tint = Color.Black
            )
        }
        Text(
            text = "${product.rating}".byLocate(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "",
            tint = Color(0xffFFB800)
        )
        IconButton(
            onClick = {
                if (isHaveFavorite){
                    mainViewModel.deletedFavoriteItem(itemId = product.id)
                }else{
                    mainViewModel.upsertFavoriteItem(product.convertToFavoriteItem())
                }
            }
        ) {
            AnimatedContent(
                targetState = isHaveFavorite, label = ""
            ) { hasFave->
                if(hasFave){
                    Icon(
                        imageVector = Icons.Outlined.Bookmark,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(26.dp)
                    )
                }else{
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(26.dp)
                    )
                }

            }

        }

    }
}