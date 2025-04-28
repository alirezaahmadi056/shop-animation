package info.alirezaahmadi.animatedshop.ui.screen.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.data.model.Category

@Composable
fun HomeCategorySection(
    categoryList: List<Category>
) {
    Text(
        text = "دسته بندی",
        modifier = Modifier.padding( 12.dp),
        color = Color.Black,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.Center
    ) {
        categoryList.forEach { category ->
            HomeCategoryItemCard(
                categoryIcon = category.icon,
                categoryName = category.title
            )

        }
    }
}

@Composable
fun HomeCategoryItemCard(
    @DrawableRes categoryIcon: Int,
    categoryName: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.95f/3)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(categoryIcon),
            contentDescription = "",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = categoryName,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(4.dp))
    }
}