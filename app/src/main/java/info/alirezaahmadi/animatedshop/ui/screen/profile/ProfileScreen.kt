package info.alirezaahmadi.animatedshop.ui.screen.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.navigation.Routes
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

private data class ProfileItems(
    val name: String,
    val icon: Int,
    val onClickRoute: Routes? = null,
)

@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    val itemList = listOf(
        ProfileItems(
            name = "سفارشات من",
            icon = R.drawable.shopping_cart1,
        ),
        ProfileItems(
            name = "تجربه های خرید من",
            icon = R.drawable.shopping_cart2,
        ),
        ProfileItems(
            name = "تغییر رمز عبور",
            icon = R.drawable.shopping_cart5,
        ),
        ProfileItems(
            name = "اعلانات من",
            icon = R.drawable.shopping_cart4,
        ),
        ProfileItems(
            name = "علاقه مندی های من",
            icon = R.drawable.shopping_cart3,
            onClickRoute = Routes.FavoriteScreen
        ),
        ProfileItems(
            name = "آدرس های من",
            icon = R.drawable.shopping_cart6,
        ),


        )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { mainViewModel.setUserProfile(it.toString()) }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader(
            mainViewModel = mainViewModel,
            editIcon = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(0.4f))
                        .clickable { navHostController.navigate(Routes.EditProfileScreen) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = "",
                        modifier = Modifier.padding(6.dp),
                        tint = Color.White
                    )
                }
            },
            changeProfileIcon = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(0.4f))
                        .clickable {
                            launcher.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_photo),
                        contentDescription = "",
                        modifier = Modifier.padding(6.dp),
                        tint = Color.White
                    )
                }
            }
        )
        Spacer(Modifier.height(12.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            itemList.forEach {
                ProfileItemCards(
                    profileItems = it,
                    navHostController = navHostController
                )
            }
        }

    }

}

@Composable
private fun ProfileItemCards(
    profileItems: ProfileItems,
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(8.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color.White)
            .clickable(enabled = profileItems.onClickRoute != null) {
                profileItems.onClickRoute?.let { navHostController.navigate(it) }
            }
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painterResource(profileItems.icon),
            contentDescription = "",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = profileItems.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}

