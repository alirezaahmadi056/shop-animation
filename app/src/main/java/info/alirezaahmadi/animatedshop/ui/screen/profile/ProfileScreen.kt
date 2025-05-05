package info.alirezaahmadi.animatedshop.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.navigation.Routes
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        ProfileHeader(
            mainViewModel = mainViewModel,
            editIcon = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(0.4f))
                        .clickable{navHostController.navigate(Routes.EditProfileScreen)},
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
                        .background(Color.LightGray.copy(0.4f)),
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

    }

}

