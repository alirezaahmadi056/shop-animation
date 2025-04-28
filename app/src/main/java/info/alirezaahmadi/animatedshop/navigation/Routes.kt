package info.alirezaahmadi.animatedshop.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object HomeScreen :Routes

    @Serializable
    data class ProductDetailScreen(
        val image:Int,
        val name:String,
    ):Routes
    @Serializable
    data object CategoryScreen :Routes
    @Serializable
    data object ShoppingCartScreen :Routes
    @Serializable
    data object ProfileScreen :Routes
}