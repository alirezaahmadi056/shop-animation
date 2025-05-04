package info.alirezaahmadi.animatedshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import info.alirezaahmadi.animatedshop.data.db.dao.FavoriteDao
import info.alirezaahmadi.animatedshop.data.db.dao.ShoppingDao
import info.alirezaahmadi.animatedshop.data.db.dao.UserDao
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import info.alirezaahmadi.animatedshop.data.db.entity.UserEntity

@Database(entities = [ShoppingEntity::class, FavoriteEntity::class, UserEntity::class], version = 1)
abstract class AppDatBase : RoomDatabase() {

    abstract fun ShoppingDao(): ShoppingDao
    abstract fun FavoriteDao(): FavoriteDao
    abstract fun UserDao():UserDao
}