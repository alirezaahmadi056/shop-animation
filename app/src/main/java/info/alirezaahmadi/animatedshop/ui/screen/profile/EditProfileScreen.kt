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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun EditProfileScreen(
    navHostController: NavHostController
) {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader()
        // نام و نام خانوادگی
        TextLabel("نام و نام خانوادگی:")
        CustomTextField(
            value = fullName,
            hint = "نام و نام خانوادگی خود را وارد کنید",
            onChangeValue = { fullName = it }
        )

        // شماره همراه
        TextLabel("شماره همراه:")
        CustomTextField(
            value = phoneNumber,
            hint = "تلفن خود را وارد کنید",
            onChangeValue = { phoneNumber = it }
        )

        // ایمیل
        TextLabel("ایمیل:")
        CustomTextField(
            value = email,
            hint = "ایمیل خود را وارد کنید",
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
                .clickable {  },
            contentAlignment = Alignment.Center
        ){
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
    onChangeValue: (String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = onChangeValue,
        shape = RoundedCornerShape(12.dp),
        textStyle = MaterialTheme.typography.titleMedium,
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