package info.alirezaahmadi.animatedshop.ui.screen.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.data.model.Product
import info.alirezaahmadi.animatedshop.navigation.Routes
import info.alirezaahmadi.animatedshop.ui.component.BestSellerProductTopSection
import info.alirezaahmadi.animatedshop.ui.component.ProductItemCard
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    mainViewModel: MainViewModel
) {
    val allCategory by mainViewModel.allCategory.collectAsState()
    val homeProduct = remember { Product.homeProduct }
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState
    ) {
        item(key = "SliderSection") { SliderSection() }
        item(key = "Spacer") { Spacer(Modifier.height(8.dp)) }
        item(key = "HomeCategorySection") {
            HomeCategorySection(categoryList = allCategory) { index ->
                navHostController.navigate(Routes.CategoryScreen(index))
            }
        }
        item(key = "BestSellerProductTopSection") { BestSellerProductTopSection() }
        item(key = "homeProduct") {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(items = homeProduct, key = { it.id }) {
                    ProductItemCard(
                        product = it,
                        navHostController = navHostController,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope
                    )
                }
            }
        }
    }

}