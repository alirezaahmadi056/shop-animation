package info.alirezaahmadi.animatedshop.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object HomeScreen
}