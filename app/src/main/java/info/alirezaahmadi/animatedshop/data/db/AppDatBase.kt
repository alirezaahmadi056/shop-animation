package info.alirezaahmadi.animatedshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import info.alirezaahmadi.animatedshop.data.db.dao.ShoppingDao
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
@TypeConverters(MyTypeConverters::class)
@Database(entities = [ShoppingEntity::class], version = 1)
abstract class AppDatBase:RoomDatabase() {
    abstract fun ShoppingDao() :ShoppingDao
}