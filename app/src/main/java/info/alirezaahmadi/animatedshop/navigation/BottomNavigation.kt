package info.alirezaahmadi.animatedshop.navigation

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.ui.component.VerticalAnimationVisibility

private data class NavigationItem(
    val name: String,
    val routes: Routes,
    @DrawableRes val icon: Int
)

@Composable
fun BottomNavigation(
    navHostController: NavHostController
) {
    val navItem = listOf(
        NavigationItem(
            name = "خانه",
            routes = Routes.HomeScreen,
            icon = R.drawable.home_nav
        ),
        NavigationItem(
            name = "دسته بندی",
            routes = Routes.CategoryScreen(),
            icon = R.drawable.category_nav
        ),
        NavigationItem(
            name = "سبد خرید",
            routes = Routes.ShoppingCartScreen,
            icon = R.drawable.shopping_nav
        ),
        NavigationItem(
            name = "پروفایل من",
            routes = Routes.ProfileScreen,
            icon = R.drawable.profile_nav
        ),

        )
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val fullRoute = backStackEntry.value?.destination?.route
    val currentRoute =
        fullRoute?.substringAfterLast(".")?.substringBefore("?")// فقط اسم ساده
    val isShow = navItem.any { it.routes.toString().substringBefore("(") == currentRoute }
    VerticalAnimationVisibility(
        isShow = isShow
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
        ) {
            navItem.forEach { nav ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        indicatorColor = Color(0xffEF472C),
                        unselectedIconColor = Color.Black,
                        selectedTextColor = Color(0xffEF472C),
                        unselectedTextColor = Color.Black
                    ),
                    selected = currentRoute == nav.routes.toString().substringBefore("(") ,
                    icon = {
                        Icon(
                            painter = painterResource(nav.icon),
                            contentDescription = "",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = nav.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = { navHostController.navigateSingle(nav.routes) }
                )
            }
        }

    }
}

private fun NavHostController.navigateSingle(routes: Routes) {
    navigate(routes) {
        popUpTo<Routes.HomeScreen> {
            inclusive = false
        }
        launchSingleTop =true
    }
}

