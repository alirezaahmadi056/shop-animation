package info.alirezaahmadi.animatedshop.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import info.alirezaahmadi.animatedshop.ui.screen.category.CategoryScreen
import info.alirezaahmadi.animatedshop.ui.screen.detail.ProductDetailScreen
import info.alirezaahmadi.animatedshop.ui.screen.home.HomeScreen
import info.alirezaahmadi.animatedshop.ui.screen.profile.ProfileScreen
import info.alirezaahmadi.animatedshop.ui.screen.shoping.ShoppingCartScreen
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    modifier: Modifier,
    mainViewModel: MainViewModel
) {
    SharedTransitionLayout {
        val navHostController = rememberNavController()
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
                    image = args.image,
                    name = args.name,
                    mainViewModel = mainViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable<Routes.CategoryScreen> {
                CategoryScreen(
                    navHostController = navHostController,
                    mainViewModel = mainViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable<Routes.ShoppingCartScreen> {
                ShoppingCartScreen(navHostController)
            }
            composable<Routes.ShoppingCartScreen> {
                ProfileScreen(navHostController)
            }

        }
    }

}