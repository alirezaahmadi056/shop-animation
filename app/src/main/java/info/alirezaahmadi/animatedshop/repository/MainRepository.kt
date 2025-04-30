package info.alirezaahmadi.animatedshop.repository

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import info.alirezaahmadi.animatedshop.data.db.dao.FavoriteDao
import info.alirezaahmadi.animatedshop.data.db.dao.ShoppingDao
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import info.alirezaahmadi.animatedshop.data.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val favoriteDao: FavoriteDao
) {

    fun getAllCategory(): List<Category> {
        return Category.fakeCategories
    }

    //shopping
    suspend fun upsertShoppingItem(shoppingEntity: ShoppingEntity) {
        shoppingDao.upsertShoppingItem(shoppingEntity)
    }

    fun getSingleShoppingItem(itemId: Int): Flow<ShoppingEntity?> =
        shoppingDao.getSingleShoppingItem(itemId)

    fun getAllShoppingItems(): Flow<List<ShoppingEntity>> =
        shoppingDao.getAllShoppingItems()

    suspend fun deletedShoppingItem(shoppingEntity: ShoppingEntity) {
        shoppingDao.deletedShoppingItem(shoppingEntity)
    }

    suspend fun updateShoppingItemCount(
        itemId: Int,
        newCount: Int,
    ) {
        shoppingDao.updateCount(itemId = itemId, newCount = newCount)
    }

    fun isHaveItemToCart(itemId: Int): Flow<Boolean> = shoppingDao.isHasIsCart(itemId)


    //favorite

    suspend fun upsertFavoriteItem(favoriteEntity: FavoriteEntity){
        favoriteDao.upsertFavoriteItem(favoriteEntity)
    }

    suspend fun deletedFavoriteItem(itemId: Int){
        favoriteDao.deletedFavoriteItem(itemId)
    }

    fun isFavorite(itemId: Int): Flow<Boolean> =favoriteDao.isFavorite(itemId)

    fun getAllFavorite(): Flow<List<FavoriteEntity>> =favoriteDao.getAllFavorite()
}