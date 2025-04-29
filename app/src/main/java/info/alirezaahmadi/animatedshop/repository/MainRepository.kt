package info.alirezaahmadi.animatedshop.repository

import info.alirezaahmadi.animatedshop.data.db.dao.ShoppingDao
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import info.alirezaahmadi.animatedshop.data.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val shoppingDao: ShoppingDao
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

}