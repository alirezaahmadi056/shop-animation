package info.alirezaahmadi.animatedshop.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object HomeScreen : Routes

    @Serializable
    data class ProductDetailScreen(
        val id: Int,
        val title: String,
        val price: Long,
        val discountPercent: Int,
        val image: Int,
        val rating: Float,
    ) : Routes

    @Serializable
    data class CategoryScreen(val selectedIndex:Int=0) : Routes

    @Serializable
    data object ShoppingCartScreen : Routes

    @Serializable
    data object ProfileScreen : Routes
    @Serializable
    data object EditProfileScreen : Routes
    @Serializable
    data object FavoriteScreen : Routes

}