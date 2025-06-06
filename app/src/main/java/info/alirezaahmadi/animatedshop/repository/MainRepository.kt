package info.alirezaahmadi.animatedshop.repository

import info.alirezaahmadi.animatedshop.data.db.dao.FavoriteDao
import info.alirezaahmadi.animatedshop.data.db.dao.ShoppingDao
import info.alirezaahmadi.animatedshop.data.db.dao.UserDao
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import info.alirezaahmadi.animatedshop.data.db.entity.UserEntity
import info.alirezaahmadi.animatedshop.data.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val favoriteDao: FavoriteDao,
    private val userDao: UserDao
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
    suspend fun deletedShoppingItemByID(productId:Int){
        shoppingDao.deletedShoppingItemByID(productId)
    }
    suspend fun updateShoppingItemCount(
        itemId: Int,
        newCount: Int,
    ) {
        shoppingDao.updateCount(itemId = itemId, newCount = newCount)
    }

    fun isHaveItemToCart(itemId: Int): Flow<Boolean> = shoppingDao.isHasIsCart(itemId)
    fun getShoppingCountById(itemId: Int): Flow<Int> =shoppingDao.getShoppingCountById(itemId)

    //favorite

    suspend fun upsertFavoriteItem(favoriteEntity: FavoriteEntity){
        favoriteDao.upsertFavoriteItem(favoriteEntity)
    }

    suspend fun deletedFavoriteItem(itemId: Int){
        favoriteDao.deletedFavoriteItem(itemId)
    }

    fun isFavorite(itemId: Int): Flow<Boolean> =favoriteDao.isFavorite(itemId)

    fun getAllFavorite(): Flow<List<FavoriteEntity>> =favoriteDao.getAllFavorite()

    //user
    suspend fun upsertUser(userEntity: UserEntity){
        userDao.upsertUser(userEntity)
    }
    fun getUser():Flow<UserEntity?> = userDao.getUser()
    suspend fun setUserProfile(profile: String){userDao.setUserProfile(profile)}

}