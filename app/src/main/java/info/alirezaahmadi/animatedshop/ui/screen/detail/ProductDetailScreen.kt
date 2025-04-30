package info.alirezaahmadi.animatedshop.ui.screen.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.data.model.Product
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductDetailScreen(
    navHostController: NavHostController,
    id: Int,
    title: String,
    description: String,
    price: Long,
    discountPercent: Int,
    image: Int,
    rating: Float,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    mainViewModel: MainViewModel
) {
    val singleProduct =Product(
        id =id,
        title= title,
        description=description,
        price= price,
        discountPercent= discountPercent,
        image=image,
        rating=rating,
    )
    with(sharedTransitionScope) {
        Scaffold(
            containerColor = Color(0xffEBEBEB)
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(15.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 15.dp),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(image),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .sharedElement(
                                sharedContentState = sharedTransitionScope.rememberSharedContentState(
                                    key = "image-${image}"
                                ),
                                animatedVisibilityScope = animatedContentScope,
                            )
                            .fillMaxWidth(0.8f)
                    )
                    Box(
                        modifier = Modifier.align(Alignment.TopStart)
                            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                            .background(Brush.linearGradient(listOf(Color(0xffFD583D),Color(0xffE32A0D),))),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "$discountPercent%".byLocate(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 6.dp)
                        )
                    }
                }

                ProductDetailHeaderSection(
                    modifier = Modifier.align(Alignment.End),
                    product = singleProduct,
                    mainViewModel = mainViewModel
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(horizontal = 12.dp)
                ) {
                    Spacer(Modifier.height(25.dp))
                    Text(
                        text =title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth().sharedElement(
                            sharedContentState = sharedTransitionScope.rememberSharedContentState(key = "text-${title}"),
                            animatedVisibilityScope =animatedContentScope
                        )
                    )

                }
            }

        }
    }
}