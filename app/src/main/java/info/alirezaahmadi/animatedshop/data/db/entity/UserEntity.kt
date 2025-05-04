package info.alirezaahmadi.animatedshop.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id:Int =0,
    val name:String,
    val phone:String,
    val email:String,
    val gender:String
)