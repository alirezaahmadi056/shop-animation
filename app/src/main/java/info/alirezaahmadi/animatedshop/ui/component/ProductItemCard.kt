package info.alirezaahmadi.animatedshop.ui.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
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
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.data.model.Product
import info.alirezaahmadi.animatedshop.navigation.Routes
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.util.byLocateAndSeparator
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductItemCard(
    product: Product,
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val isProductInBasket = mainViewModel.isHaveItemToCart(product.id).collectAsState(false)
    with(sharedTransitionScope) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .width(220.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(Color(0xffEBEBEB))
                .clickable {
                    navHostController.navigate(
                        Routes.ProductDetailScreen(
                            id = product.id,
                            image = product.image,
                            title = product.title,
                            price = product.price,
                            rating = product.rating,
                            discountPercent = product.discountPercent,
                        )
                    )
                },
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(product.image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(135.dp)
                        .sharedElement(
                            sharedContentState = sharedTransitionScope.rememberSharedContentState(
                                key = "image-${product.image}"
                            ),
                            animatedVisibilityScope = animatedContentScope
                        ),
                    contentScale = ContentScale.Fit
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.sharedElement(
                            sharedContentState = sharedTransitionScope.rememberSharedContentState(
                                key = "text-${product.title}"
                            ),
                            animatedVisibilityScope = animatedContentScope,
                        )
                    )
                    val discountPrice = (product.price * (100 - product.discountPercent)) / 100
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (product.discountPercent > 0) {
                            Text(
                                text = product.price.toString().byLocateAndSeparator(),
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
            if (!isProductInBasket.value){
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 37.dp, end = 2.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(Color(0xffFD583D), Color(0xffE32A0D))))
                        .clickable { mainViewModel.upsertShoppingItem(product.convertToShoppingItem(1)) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            if (product.discountPercent > 0) {
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
                        text = "${(product.discountPercent).toString().byLocate()}% ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

        }

    }
}
