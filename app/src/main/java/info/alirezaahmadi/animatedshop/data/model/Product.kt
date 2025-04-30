package info.alirezaahmadi.animatedshop.data.model

import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Long,
    val discountPercent: Int,
    val image: Int,
    val rating: Float,
){
    fun convertToFavoriteItem():FavoriteEntity{
        return  FavoriteEntity(id, title, description, price, discountPercent, image, rating)
    }
}

data class Category(
    val id: Int,
    val title: String,
    val icon: Int,
    val products: List<Product>
){
  companion object {
        val fakeCategories = listOf(
            Category(
                id = 1,
                title = "دخترانه",
                icon = R.drawable.category_baby_dress,
                products = listOf(
                    Product(
                        id = 11,
                        title = "پیراهن دخترانه صورتی",
                        description = "پیراهن زیبا و راحت مناسب مجالس.",
                        price = 450_000L,
                        discountPercent = 10,
                        image = R.drawable.img_shirt,
                        rating = 4.6f,
                    ),
                    Product(
                        id = 12,
                        title = "تی‌شرت دخترانه",
                        description = "تی‌شرت خنک تابستانی.",
                        price = 220_000L,
                        discountPercent = 5,
                        image = R.drawable.img_shirt6,
                        rating = 4.4f,
                    )
                )
            ),
            Category(
                id = 2,
                title = "مردانه",
                icon = R.drawable.category_polo,
                products = listOf(
                    Product(
                        id = 21,
                        title = "ست سویشرت و شلوار مردانه",
                        description = "سویشرت راحت مناسب پاییز و زمستان.",
                        price = 1_550_000L,
                        discountPercent = 10,
                        image = R.drawable.img_shirt1,
                        rating = 4.5f,
                    )
                )
            ),
            Category(
                id = 3,
                title = "زنانه",
                icon = R.drawable.category_woman,
                products = listOf(
                    Product(
                        id = 31,
                        title = "پیراهن مجلسی زنانه",
                        description = "پیراهن شیک و مجلسی مناسب مهمانی‌ها.",
                        price = 1_200_000L,
                        discountPercent = 15,
                        image = R.drawable.img_shirt8,
                        rating = 4.7f,
                    )
                )
            ),
            Category(
                id = 4,
                title = "کفش",
                icon = R.drawable.category_shoes,
                products = listOf(
                    Product(
                        id = 41,
                        title = "کفش اسپرت مردانه",
                        description = "کفش مناسب برای استفاده روزمره و ورزش.",
                        price = 980_000L,
                        discountPercent = 20,
                        image = R.drawable.img_shirt7,
                        rating = 4.3f,
                    ),
                    Product(
                        id = 42,
                        title = "کفش دخترانه رنگی",
                        description = "کفش راحتی مناسب کودکان.",
                        price = 370_000L,
                        discountPercent = 5,
                        image = R.drawable.img_shirt7,
                        rating = 4.5f,
                    )
                )
            ),
            Category(
                id = 5,
                title = "نوزادی",
                icon = R.drawable.category_onesie,
                products = listOf(
                    Product(
                        id = 51,
                        title = "لباس نوزادی طرح خرس",
                        description = "لباس راحت و نرم برای نوزادان.",
                        price = 250_000L,
                        discountPercent = 8,
                        image = R.drawable.img_shirt5,
                        rating = 4.8f,
                    )
                )
            ),
            Category(
                id = 6,
                title = "پسرانه",
                icon = R.drawable.category_tshirt,
                products = listOf(
                    Product(
                        id = 61,
                        title = "تی‌شرت پسرانه اسپرت",
                        description = "تی‌شرت خنک و اسپرت برای پسران.",
                        price = 300_000L,
                        discountPercent = 12,
                        image = R.drawable.img_shirt10,
                        rating = 4.6f,
                    )
                )
            )
        )
    }
}
