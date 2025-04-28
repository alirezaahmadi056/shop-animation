package info.alirezaahmadi.animatedshop.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.data.model.Category

@Composable
fun HomeCategorySection() {
FlowRow(
    modifier = Modifier.fillMaxWidth(),
    maxItemsInEachRow = 3,
    horizontalArrangement = Arrangement.SpaceAround
) {
    repeat(6){
        HomeCategoryItemCard()
    }
}
}
@Preview(locale = "fa")
@Composable
fun HomeCategoryItemCard(
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .size(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.polo_shirt),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "category",
            color = Color.Black
        )
    }
}