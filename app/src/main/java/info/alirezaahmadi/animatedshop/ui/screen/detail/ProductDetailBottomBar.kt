package info.alirezaahmadi.animatedshop.ui.screen.detail

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.data.model.Product
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.util.byLocateAndSeparator
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun ProductDetailBottomBar(
    product: Product,
    mainViewModel: MainViewModel
) {

    val countInBasket by mainViewModel.getShoppingCountById(product.id).collectAsState(1)
    Column(
        modifier = Modifier
            .background(Color(0xFCF3ECE5))
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        val discountPrice =
            (product.price - (product.price * product.discountPercent) / 100)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.price.toString().byLocateAndSeparator(),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.LineThrough
            )
            Text(
                text = discountPrice.toInt().toString().byLocateAndSeparator() + " تومان",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(Modifier.height(8.dp))
        AnimatedContent(
            targetState = countInBasket < 1, label = ""
        ) { showAdd ->
            if (showAdd) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xffE02508),
                                    Color(0xffFE593E)
                                )
                            )
                        )
                        .clickable {
                            mainViewModel.upsertShoppingItem(
                                product.convertToShoppingItem(count = 1)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "افزودن به سبد خرید",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp)
                    )
                }
            } else {
                SectionUpdateCountBasket(
                    productId = product.id,
                    count = countInBasket,
                    mainViewModel = mainViewModel
                )
            }
        }
        Spacer(Modifier.height(4.dp))
    }
}

@Composable
private fun SectionUpdateCountBasket(
    count: Int,
    productId: Int,
    mainViewModel: MainViewModel
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .clickable {
                    mainViewModel.updateShoppingItemCount(
                        itemId = productId,
                        newCount = count + 1
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_increase_24),
                contentDescription = "",
                tint = Color(0xff55A066),
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
        Text(
            text = count.toString().byLocate(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .clickable {
                    if (count < 2) {
                        mainViewModel.deletedShoppingItemByID(productId = productId)
                    } else {
                        mainViewModel.updateShoppingItemCount(
                            itemId = productId,
                            newCount = count - 1
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(if (count < 2) R.drawable.ic_delete else R.drawable.ic_decrease_24),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
    }

}