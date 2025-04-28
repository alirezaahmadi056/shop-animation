package info.alirezaahmadi.animatedshop.ui.screen.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.navigation.Routes

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item { SliderSection() }
        item { HomeCategorySection()  }
        item {
            with(sharedTransitionScope) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { navHostController.navigate(Routes.ProductDetailScreen(name = "loli", image = R.drawable.polo_shirt)) }
                ) {
                    Image(
                        painterResource(id = R.drawable.polo_shirt),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "image-${R.drawable.polo_shirt}"),
                                animatedVisibilityScope = animatedContentScope
                            )
                            .size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "loli", fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "text-loli"),
                                animatedVisibilityScope = animatedContentScope,
                            )
                    )
                }
            }

        }
    }

}