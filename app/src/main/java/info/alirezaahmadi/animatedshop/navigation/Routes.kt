package info.alirezaahmadi.animatedshop.navigation

import info.alirezaahmadi.animatedshop.data.model.Product
import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object HomeScreen : Routes

    @Serializable
    data class ProductDetailScreen(
        val id: Int,
        val title: String,
        val description: String,
        val price: Long,
        val discountPercent: Int,
        val image: Int,
        val rating: Float,
    ) : Routes

    @Serializable
    data object CategoryScreen : Routes

    @Serializable
    data object ShoppingCartScreen : Routes

    @Serializable
    data object ProfileScreen : Routes
}