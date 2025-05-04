package info.alirezaahmadi.animatedshop.ui.screen.shoping

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import info.alirezaahmadi.animatedshop.data.model.ShoppingSummary
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel


@Composable
fun ShoppingCartScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
) {

    val products by mainViewModel.getAllShoppingItems().collectAsState(emptyList())

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = { BasketBottomBar(shoppingSummary = calculatePriceSummary(products)) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding
        ) {
            items(items = products, key = {it.id}) { product ->
                ShoppingItemCard(
                    shoppingEntity = product,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}
fun calculatePriceSummary(shoppingList: List<ShoppingEntity>): ShoppingSummary {
    var totalPrice = 0L
    var totalDiscount = 0L

    for (item in shoppingList) {
        val itemTotal = item.price * item.count
        val itemDiscount = itemTotal * item.discountPercent / 100

        totalPrice += itemTotal
        totalDiscount += itemDiscount
    }

    val finalPrice = totalPrice - totalDiscount

    return ShoppingSummary(
        totalPrice = totalPrice,
        totalDiscount = totalDiscount,
        finalPrice = finalPrice
    )
}
