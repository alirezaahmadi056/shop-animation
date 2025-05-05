package info.alirezaahmadi.animatedshop.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import info.alirezaahmadi.animatedshop.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(userEntity: UserEntity)

    @Query("select * from userentity where id=0")
    fun getUser(): Flow<UserEntity?>
}