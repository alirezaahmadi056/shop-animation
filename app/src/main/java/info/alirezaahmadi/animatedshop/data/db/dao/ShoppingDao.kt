package info.alirezaahmadi.animatedshop.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Upsert
    suspend fun upsertShoppingItem(shoppingEntity: ShoppingEntity)

    @Query("select *  from shoppingentity where id=:itemId ")
    fun getSingleShoppingItem(itemId: Int): Flow<ShoppingEntity?>

    @Query("select * from shoppingentity")
    fun getAllShoppingItems(): Flow<List<ShoppingEntity>>

    @Delete
    suspend fun deletedShoppingItem(shoppingEntity: ShoppingEntity)

    @Query("delete from shoppingentity where id=:productId")
    suspend fun deletedShoppingItemByID(productId:Int)

    @Query("update ShoppingEntity set count=:newCount where id=:itemId")
    suspend fun updateCount(
        itemId: Int,
        newCount: Int,
    )

    @Query("select exists(select * from ShoppingEntity where id=:itemId) ")
    fun isHasIsCart(itemId: Int): Flow<Boolean>

    @Query("select count from ShoppingEntity where id=:itemId")
    fun getShoppingCountById(itemId: Int): Flow<Int>
}