package info.alirezaahmadi.animatedshop.ui.screen.shoping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.util.byLocateAndSeparator
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun ShoppingItemCard(
    shoppingEntity: ShoppingEntity,
    mainViewModel: MainViewModel
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xffFCF3EC)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                mainViewModel.updateShoppingItemCount(
                    itemId = shoppingEntity.id,
                    newCount = shoppingEntity.count + 1
                )
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_increase_24),
                    contentDescription = "",
                    tint = Color(0xff55A066)
                )
            }
            Text(
                text = shoppingEntity.count.toString().byLocate(),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = {
                if (shoppingEntity.count < 2) {
                    mainViewModel.deletedShoppingItemByID(productId = shoppingEntity.id)
                } else {
                    mainViewModel.updateShoppingItemCount(
                        itemId = shoppingEntity.id,
                        newCount = shoppingEntity.count - 1
                    )
                }
            }) {
                Icon(
                    painter = painterResource(if (shoppingEntity.count < 2) R.drawable.ic_delete else R.drawable.ic_decrease_24),
                    contentDescription = "",
                    tint = Color(0xffEF472C)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(0.25f)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xffEBEBEB)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(shoppingEntity.image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .weight(0.65f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = shoppingEntity.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val discountPrice =
                    (shoppingEntity.price * (100 - shoppingEntity.discountPercent)) / 100

                Text(
                    text = shoppingEntity.price.toString().byLocateAndSeparator(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    text = "${discountPrice.toString().byLocateAndSeparator()} تومان",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )

            }
        }
    }

}