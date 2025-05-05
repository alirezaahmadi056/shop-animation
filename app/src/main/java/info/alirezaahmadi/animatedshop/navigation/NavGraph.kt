package info.alirezaahmadi.animatedshop.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import info.alirezaahmadi.animatedshop.ui.screen.category.CategoryScreen
import info.alirezaahmadi.animatedshop.ui.screen.detail.ProductDetailScreen
import info.alirezaahmadi.animatedshop.ui.screen.favorit.FavoriteScreen
import info.alirezaahmadi.animatedshop.ui.screen.home.HomeScreen
import info.alirezaahmadi.animatedshop.ui.screen.profile.EditProfileScreen
import info.alirezaahmadi.animatedshop.ui.screen.profile.ProfileScreen
import info.alirezaahmadi.animatedshop.ui.screen.shoping.ShoppingCartScreen
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    modifier: Modifier,
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    SharedTransitionLayout {
        NavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = Routes.HomeScreen
        ) {
            composable<Routes.HomeScreen> {
                HomeScreen(
                    navHostController = navHostController,
                    mainViewModel = mainViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable<Routes.ProductDetailScreen> {
                val args = it.toRoute<Routes.ProductDetailScreen>()
                ProductDetailScreen(
                    navHostController = navHostController,
                    id =args.id,
                    image = args.image,
                    title = args.title,
                    price = args.price,
                    rating = args.rating,
                    discountPercent = args.discountPercent,
                    mainViewModel = mainViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable<Routes.CategoryScreen> {
                val args =it.toRoute<Routes.CategoryScreen>()
                CategoryScreen(
                    navHostController = navHostController,
                    selectedTabIndex = args.selectedIndex,
                    mainViewModel = mainViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable<Routes.ShoppingCartScreen> {
                ShoppingCartScreen(navHostController,mainViewModel)
            }
            composable<Routes.ProfileScreen> {
                ProfileScreen(navHostController,mainViewModel)
            }
            composable<Routes.EditProfileScreen> {
                EditProfileScreen(navHostController,mainViewModel)
            }
            composable<Routes.FavoriteScreen> {
                FavoriteScreen(mainViewModel)
            }

        }
    }

}