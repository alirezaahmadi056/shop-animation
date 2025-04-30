package info.alirezaahmadi.animatedshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import info.alirezaahmadi.animatedshop.data.db.dao.FavoriteDao
import info.alirezaahmadi.animatedshop.data.db.dao.ShoppingDao
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity

@Database(entities = [ShoppingEntity::class,FavoriteEntity::class], version = 1)
abstract class AppDatBase:RoomDatabase() {

    abstract fun ShoppingDao() :ShoppingDao
    abstract fun FavoriteDao():FavoriteDao
}