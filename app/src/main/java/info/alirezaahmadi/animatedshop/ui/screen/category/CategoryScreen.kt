package info.alirezaahmadi.animatedshop.ui.screen.category

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.ui.component.BestSellerProductTopSection
import info.alirezaahmadi.animatedshop.ui.component.ProductItemCard
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CategoryScreen(
    navHostController: NavHostController,
    selectedTabIndex: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    mainViewModel: MainViewModel
) {
    val allCategory by mainViewModel.allCategory.collectAsState()
    val pagerState = rememberPagerState(selectedTabIndex) { allCategory.size }
    Scaffold(
        topBar = {
            CategoryTabsSection(
                pagerState = pagerState,
                categoryList = allCategory
            )
        },
        containerColor = Color(0xffFCF3EC),
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding,
            key = { "page$it" },
            userScrollEnabled = false
        ) { page ->
            val product = remember(
                key1 = page,
                key2 = allCategory
            ) { allCategory[page].products }

                 val bestSellerProduct = remember(
                key1 = page,
                key2 = allCategory
            ) { allCategory[page].bestProduct }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item(key = "title") {
                    Text(
                        text = allCategory[page].title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
                item(key = "product") {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(product, key = { it.id }) {product->
                            ProductItemCard(
                                product = product,
                                navHostController = navHostController,
                                mainViewModel = mainViewModel,
                                sharedTransitionScope = sharedTransitionScope,
                                animatedContentScope = animatedContentScope
                            )
                        }
                    }
                }
                item(key = "BestSellerProductTopSection") { BestSellerProductTopSection() }
                item(key = "bestSellerProduct") {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(bestSellerProduct, key = { it.id }) {product->
                            ProductItemCard(
                                product = product,
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                sharedTransitionScope = sharedTransitionScope,
                                animatedContentScope = animatedContentScope
                            )
                        }
                    }
                }
            }
        }
    }
}

