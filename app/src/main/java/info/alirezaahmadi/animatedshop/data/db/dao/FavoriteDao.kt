package info.alirezaahmadi.animatedshop.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Upsert
    suspend fun upsertFavoriteItem(favoriteEntity: FavoriteEntity)

    @Query("delete from favoriteentity where id=:itemId")
    suspend fun deletedFavoriteItem(itemId: Int)

    @Query("select exists(select * from favoriteentity where id=:itemId) ")
    fun isFavorite(itemId: Int): Flow<Boolean>

    @Query("select * from favoriteentity")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>
}