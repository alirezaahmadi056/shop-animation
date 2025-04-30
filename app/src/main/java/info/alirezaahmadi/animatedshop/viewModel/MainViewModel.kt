package info.alirezaahmadi.animatedshop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity
import info.alirezaahmadi.animatedshop.data.model.Category
import info.alirezaahmadi.animatedshop.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _allCategory: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val allCategory: StateFlow<List<Category>> = _allCategory.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) { _allCategory.emit(repository.getAllCategory()) }
    }

    //shopping
    fun upsertShoppingItem(shoppingEntity: ShoppingEntity) {
        viewModelScope.launch(Dispatchers.IO) { repository.upsertShoppingItem(shoppingEntity) }
    }

    fun getSingleShoppingItem(itemId: Int): Flow<ShoppingEntity?> =
        repository.getSingleShoppingItem(itemId)

    fun getAllShoppingItems(): Flow<List<ShoppingEntity>> =
        repository.getAllShoppingItems()

    fun deletedShoppingItem(shoppingEntity: ShoppingEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletedShoppingItem(shoppingEntity)
        }
    }

    fun updateShoppingItemCount(
        itemId: Int,
        newCount: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateShoppingItemCount(itemId = itemId, newCount = newCount)
        }
    }

    fun isHaveItemToCart(itemId: Int): Flow<Boolean> = repository.isHaveItemToCart(itemId)

    //favorite
     fun upsertFavoriteItem(favoriteEntity: FavoriteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertFavoriteItem(favoriteEntity)
        }
    }

     fun deletedFavoriteItem(itemId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletedFavoriteItem(itemId)
        }

    }

    fun isFavorite(itemId: Int): Flow<Boolean> =repository.isFavorite(itemId)
    fun getAllFavorite(): Flow<List<FavoriteEntity>> =repository.getAllFavorite()
}