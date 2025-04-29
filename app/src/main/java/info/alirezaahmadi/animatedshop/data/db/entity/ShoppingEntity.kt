package info.alirezaahmadi.animatedshop.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Long,
    val discountPercent: Int,
    val image: Int,
    val rating: Float,
    val features: List<String>,
    val count:Int
)