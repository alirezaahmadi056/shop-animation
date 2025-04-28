package info.alirezaahmadi.animatedshop.repository

import info.alirezaahmadi.animatedshop.data.model.Category
import javax.inject.Inject

class MainRepository @Inject constructor(){

    private fun getAllCategory() :List<Category>{
        return Category.fakeCategories
    }
}