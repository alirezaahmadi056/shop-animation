package info.alirezaahmadi.animatedshop.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.navigation.Routes
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun AppTopBar(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    openDrawer:()-> Unit,
) {
    val user = mainViewModel.getUser().collectAsState(null)
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val currentGraph =
        backStackEntry.value?.destination?.route?.substringAfterLast(".")?.substringBefore("?")

    val navigationRoute = listOf(
        Routes.HomeScreen,
        Routes.CategoryScreen(),
        Routes.ShoppingCartScreen,
        Routes.ProfileScreen,
    )
    val showBack = currentGraph !in navigationRoute.map { it.toString().substringBefore("(") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.notification),
                    contentDescription = "",
                    modifier = Modifier.size(23.dp)
                )


            }
            IconButton(onClick = {navHostController.navigate(Routes.ProfileScreen)}){
                AsyncImage(
                    model = user.value?.profile,
                    contentDescription = "",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    error = painterResource(R.drawable.afshari),
                   placeholder  = painterResource(R.drawable.afshari),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.online_shop),
                contentDescription = "",
                modifier = Modifier.height(21.dp),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Fit
            )
            AnimatedVisibility(
                visible = showBack,
                enter = expandHorizontally(tween(500)),
                exit = shrinkHorizontally(tween(500)),
            ) {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        }

    }
}