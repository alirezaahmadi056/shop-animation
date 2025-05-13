package info.alirezaahmadi.animatedshop.ui.component

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.navigation.Routes
import info.alirezaahmadi.animatedshop.util.byLocate
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DrawerContent(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    closeDrawer: () -> Unit
) {
    val user = mainViewModel.getUser().collectAsState(null)
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xffFCEDDF))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            GlideImage(
                model = user.value?.profile,
                contentDescription = "",
                modifier = Modifier
                    .weight(0.15f)
                    .aspectRatio(1f)
                    .clip(CircleShape),
                failure = placeholder(R.drawable.afshari),
                contentScale = ContentScale.Crop
            )
            Text(
                text = user.value?.name?:"کاربر فروشگاه",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.weight(0.55f),
                maxLines = 1
            )
            Text(
                text = user.value?.phone?:"09159150915".byLocate(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(0.3f),
                maxLines = 1
            )
        }

        DrawerItemCard(
            name = "پروفایل کاربری",
            icon = R.drawable.profile_nav,
            onClick = { navHostController.navigate(Routes.ProfileScreen)
                closeDrawer.invoke()
            }
        )
        HorizontalDivider(
            thickness = 0.8.dp,
            color = Color.LightGray.copy(0.5f)
        )
        DrawerItemCard(
            name = "سفارشات من",
            icon = R.drawable.ic_orders,
            onClick = {
                Toast.makeText(context, "سفارشی یافت نشد", Toast.LENGTH_SHORT).show()
                closeDrawer.invoke()
            }
        )
        HorizontalDivider(
            thickness = 0.8.dp,
            color = Color.LightGray.copy(0.5f)
        )
        DrawerItemCard(
            name = "پشتیبانی",
            icon = R.drawable.img_support,
            onClick = {
                try {
                    uriHandler.openUri("tg://resolve?domain=i_hoseinam")
                } catch (_: Exception) {
                    Toast.makeText(context, "تلگرام یافت نشد", Toast.LENGTH_SHORT).show()
                }
                closeDrawer.invoke()
            }
        )
        HorizontalDivider(
            thickness = 0.8.dp,
            color = Color.LightGray.copy(0.5f)
        )
        DrawerItemCard(
            name = "قوانین و مقررات ",
            icon = R.drawable.ic_rules,
            onClick = {
                Toast.makeText(context, "قوانین نداریم", Toast.LENGTH_SHORT).show()
                closeDrawer.invoke()

            }
        )
        HorizontalDivider(
            thickness = 0.8.dp,
            color = Color.LightGray.copy(0.5f)
        )
        DrawerItemCard(
            name = "درباره ما",
            icon = R.drawable.img_people,
            onClick = {
                Toast.makeText(context, "ما بتمنیم", Toast.LENGTH_SHORT).show()
                closeDrawer.invoke()
            }
        )
        HorizontalDivider(
            thickness = 0.8.dp,
            color = Color.LightGray.copy(0.5f)
        )
        DrawerItemCard(
            name = "ارتباط با ما",
            icon = R.drawable.img_music_play,
            onClick = {
                try {
                    uriHandler.openUri("tg://resolve?domain=i_hoseinam")
                } catch (_: Exception) {
                    Toast.makeText(context, "تلگرام یافت نشد", Toast.LENGTH_SHORT).show()
                }
                closeDrawer.invoke()
            }
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                "App Version 1",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp)
            )
        }

    }
}

@Composable
fun DrawerItemCard(
    @DrawableRes icon: Int,
    name: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(14.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
            color = Color.Black,
        )
    }
}