package info.alirezaahmadi.animatedshop.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.data.db.entity.UserEntity
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun EditProfileScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
) {
    val user by mainViewModel.getUser().collectAsState(null)

    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    LaunchedEffect(user) {
        fullName =user?.name?:""
        phoneNumber =user?.phone?:""
        email =user?.email?:""
        gender =user?.gender?:""
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader(
            mainViewModel = mainViewModel,
        )
        // نام و نام خانوادگی
        TextLabel("نام و نام خانوادگی:")
        CustomTextField(
            value = fullName,
            maxLength = 30,
            keyboardType = KeyboardType.Text,
            hint = "نام و نام خانوادگی خود را وارد کنید",
            onChangeValue = { fullName = it }
        )

        // شماره همراه
        TextLabel("شماره همراه:")
        CustomTextField(
            value = phoneNumber,
            hint = "تلفن خود را وارد کنید",
            keyboardType = KeyboardType.Phone,
            maxLength = 11,
            textDirection = TextDirection.Ltr,
            onChangeValue = { phoneNumber = it }
        )

        // ایمیل
        TextLabel("ایمیل:")
        CustomTextField(
            value = email,
            hint = "ایمیل خود را وارد کنید",
            keyboardType = KeyboardType.Email,
            onChangeValue = { email = it }
        )

        // جنسیت
        TextLabel("جنسیت:")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 12.dp)
        ) {
            RadioButton(
                selected = gender == "زن",
                onClick = { gender = "زن" }
            )
            Text(
                "زن", modifier = Modifier.padding(end = 16.dp),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
            )
            RadioButton(
                selected = gender == "مرد",
                onClick = { gender = "مرد" }
            )
            Text(
                "مرد",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Brush.linearGradient(listOf(Color(0xffE02508), Color(0xffFE593E))))
                .clickable {
                    mainViewModel.upsertUser(
                        UserEntity(
                            email = email,
                            name = fullName,
                            phone = phoneNumber,
                            gender = gender,
                            profile = user?.profile.toString()
                        )
                    )
                    navHostController.navigateUp()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ثبت تغییرات",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 11.dp)
            )
        }
    }
}

@Composable
private fun CustomTextField(
    value: String,
    hint: String,
    keyboardType: KeyboardType,
    onChangeValue: (String) -> Unit,
    textDirection: TextDirection = TextDirection.Rtl,
    maxLength: Int = Int.MAX_VALUE
) {
    TextField(
        value = value.take(maxLength),
        onValueChange = {
            if (it.length <= maxLength) {
                onChangeValue(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(12.dp),
        textStyle = MaterialTheme.typography.titleMedium.copy(
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            textDirection = textDirection
        ),
        modifier = Modifier
            .imePadding()
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White.copy(0.8f),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedPlaceholderColor = Color.DarkGray,
            unfocusedPlaceholderColor = Color.DarkGray
        )
    )
}


@Composable
private fun TextLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(start = 12.dp, top = 8.dp)
    )
}