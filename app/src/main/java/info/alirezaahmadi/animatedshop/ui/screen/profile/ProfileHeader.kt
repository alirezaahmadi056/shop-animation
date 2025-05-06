package info.alirezaahmadi.animatedshop.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun ProfileHeader(
    mainViewModel: MainViewModel,
    editIcon: (@Composable () -> Unit)? = null,
    changeProfileIcon: (@Composable () -> Unit)? = null,
) {
    val user = mainViewModel.getUser().collectAsState(null)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(Brush.linearGradient(listOf(Color(0xffE32A0D), Color(0xffFD937F))))
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "پروفایل کاربری",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 12.dp)
        )
        Row(
            modifier = Modifier.padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
                changeProfileIcon?.invoke()
            Box(
                modifier = Modifier
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = {
                        0.6f
                    },
                    modifier = Modifier.size(100.dp),
                    color = Color.White,
                    strokeWidth = 4.dp,
                    trackColor = Color.Transparent,
                )
                AsyncImage(
                    model = user.value?.profile?.toUri(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.afshari),
                    error = painterResource(R.drawable.afshari),
                )
            }
            editIcon?.invoke()
        }
        Text(
            text = user.value?.name ?: "کاربر فروشگاه",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Text(
            text = user.value?.phone ?: "09159150915".byLocate(),
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

    }
}