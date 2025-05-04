package info.alirezaahmadi.animatedshop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import info.alirezaahmadi.animatedshop.data.db.AppDatBase
import info.alirezaahmadi.animatedshop.data.db.dao.FavoriteDao
import info.alirezaahmadi.animatedshop.data.db.dao.ShoppingDao
import info.alirezaahmadi.animatedshop.data.db.dao.UserDao
import info.alirezaahmadi.animatedshop.data.db.entity.UserEntity
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ): AppDatBase {
        return Room.databaseBuilder(
            context = context,
            name = "my_dp",
            klass = AppDatBase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingDao(appDatBase: AppDatBase): ShoppingDao {
        return appDatBase.ShoppingDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatBase: AppDatBase): FavoriteDao {
        return appDatBase.FavoriteDao()
    }
    @Provides
    @Singleton
    fun provideUserDaoDao(appDatBase: AppDatBase): UserDao {
        return appDatBase.UserDao()
    }


}