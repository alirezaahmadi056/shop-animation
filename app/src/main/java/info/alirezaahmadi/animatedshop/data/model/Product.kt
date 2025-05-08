package info.alirezaahmadi.animatedshop.data.model

import info.alirezaahmadi.animatedshop.R
import info.alirezaahmadi.animatedshop.data.db.entity.FavoriteEntity
import info.alirezaahmadi.animatedshop.data.db.entity.ShoppingEntity

data class Product(
    val id: Int,
    val title: String,
    val price: Long,
    val discountPercent: Int,
    val image: Int,
    val rating: Float,
) {
    companion object {
        val homeProduct = listOf(
            Product(
                id = 660,
                title = "سوییشرت سفید پروانه\u200Cای",
                price = 520_000L,
                discountPercent = 15,
                image = R.drawable.z5,
                rating = 4.7f,
            ),
            Product(
                id = 661,
                title = "شلوار نوزادی رنگی ۵ عدد",
                price = 550_000L,
                discountPercent = 30,
                image = R.drawable.n5,
                rating = 4.9f,
            ),
            Product(
                id = 662,
                title = "کتونی سفید",
                price = 850_000L,
                discountPercent = 45,
                image = R.drawable.k5,
                rating = 5f,
            ),

            Product(
                id = 663,
                title = "لباس تابستانی سفید\tای",
                price = 320_000L,
                discountPercent = 5,
                image = R.drawable.z6,
                rating = 4.4f,
            ),

            )
    }

    fun convertToFavoriteItem(): FavoriteEntity {
        return FavoriteEntity(id, title, price, discountPercent, image, rating)
    }

    fun convertToShoppingItem(count: Int): ShoppingEntity {
        return ShoppingEntity(id, title, price, discountPercent, image, rating, count = count)
    }
}

data class Category(
    val id: Int,
    val title: String,
    val icon: Int,
    val products: List<Product>,
    val bestProduct: List<Product>
) {
    companion object {
        val fakeCategories = listOf(
            Category(
                id = 1,
                title = "دخترانه",
                icon = R.drawable.category_baby_dress,
                products = listOf(
                    Product(
                        id = 11,
                        title = "تیشرت و شلوارک دخترانه",
                        price = 320_000L,
                        discountPercent = 20,
                        image = R.drawable.d1,
                        rating = 4.6f,
                    ),
                    Product(
                        id = 12,
                        title = "ست تیشرت دخترانه",
                        price = 220_000L,
                        discountPercent = 5,
                        image = R.drawable.d2,
                        rating = 4.4f,
                    ),

                    ),
                bestProduct = listOf(
                    Product(
                        id = 13,
                        title = "تاپ و دامن دخترانه",
                        price = 340_000L,
                        discountPercent = 25,
                        image = R.drawable.d3,
                        rating = 4.6f,
                    ),
                    Product(
                        id = 14,
                        title = "ست نوزادی دخترانه",
                        price = 290_000L,
                        discountPercent = 10,
                        image = R.drawable.d4,
                        rating = 4.4f,
                    ),

                    ),
            ),
            Category(
                id = 2,
                title = "مردانه",
                icon = R.drawable.category_polo,
                products = listOf(
                    Product(
                        id = 21,
                        title = "تیشرت مردانه مشکی",
                        price = 620_000L,
                        discountPercent = 30,
                        image = R.drawable.m1,
                        rating = 4.8f,
                    ),
                    Product(
                        id = 22,
                        title = "ست لباس مردانه سفید طوسی",
                        price = 580_000L,
                        discountPercent = 25,
                        image = R.drawable.m2,
                        rating = 4.8f,
                    ),

                    ),
                bestProduct = listOf(
                    Product(
                        id = 23,
                        title = "ست ورزشی سبز",
                        price = 550_000L,
                        discountPercent = 0,
                        image = R.drawable.m3,
                        rating = 4.2f,
                    ),
                    Product(
                        id = 24,
                        title = "ست مردانه زیتونی سفید",
                        price = 600_000L,
                        discountPercent = 15,
                        image = R.drawable.m4,
                        rating = 4.4f,
                    ),

                    ),
            ),
            Category(
                id = 3,
                title = "زنانه",
                icon = R.drawable.category_woman,
                products = listOf(
                    Product(
                        id = 31,
                        title = "تاپ طوسی زنانه",
                        price = 200_000L,
                        discountPercent = 10,
                        image = R.drawable.z1,
                        rating = 4.2f,
                    ),
                    Product(
                        id = 32,
                        title = "سارافون و بلوز زنانه",
                        price = 470_000L,
                        discountPercent = 30,
                        image = R.drawable.z2,
                        rating = 4.0f,
                    ),

                    ),
                bestProduct = listOf(
                    Product(
                        id = 33,
                        title = "ست راحتی زنانه مشکی",
                        price = 390_000L,
                        discountPercent = 14,
                        image = R.drawable.z3,
                        rating = 4.6f,
                    ),
                    Product(
                        id = 34,
                        title = "هودی مشکی زنانه",
                        price = 430_000L,
                        discountPercent = 7,
                        image = R.drawable.z4,
                        rating = 5.0f,
                    ),

                    ),
            ),
            Category(
                id = 4,
                title = "کفش",
                icon = R.drawable.category_shoes,
                products = listOf(
                    Product(
                        id = 41,
                        title = "کفش اسپرت ساق\u200Cدار مشکی",
                        price = 580_000L,
                        discountPercent = 18,
                        image = R.drawable.k1,
                        rating = 4.3f,
                    ),
                    Product(
                        id = 42,
                        title = "کفش اسپرت سفید",
                        price = 520_000L,
                        discountPercent = 12,
                        image = R.drawable.k2,
                        rating = 4.5f,
                    )
                ),
                bestProduct = listOf(
                    Product(
                        id = 43,
                        title = "کتانی بژ دخترانه",
                        price = 480_000L,
                        discountPercent = 20,
                        image = R.drawable.k3,
                        rating = 4.9f,
                    ),
                    Product(
                        id = 44,
                        title = "کفش اسپرت مشکی سفید",
                        price = 530_000L,
                        discountPercent = 16,
                        image = R.drawable.k4,
                        rating = 4.4f,
                    )
                ),
            ),
            Category(
                id = 5,
                title = "نوزادی",
                icon = R.drawable.category_onesie,
                products = listOf(
                    Product(
                        id = 51,
                        title = "ست نوزادی با کلاه",
                        price = 310_000L,
                        discountPercent = 10,
                        image = R.drawable.n1,
                        rating = 4.8f,
                    ),
                    Product(
                        id = 52,
                        title = "ست لباس نوزادی کرم قهوه\u200Cای",
                        price = 290_000L,
                        discountPercent = 20,
                        image = R.drawable.n2,
                        rating = 4.8f,
                    ),

                    ),
                bestProduct = listOf(
                    Product(
                        id = 53,
                        title = "لباس نوزادی صورتی خرس",
                        price = 260_000L,
                        discountPercent = 15,
                        image = R.drawable.n3,
                        rating = 4.2f,
                    ),
                    Product(
                        id = 54,
                        title = "ست لباس نوزادی دوقلو\tای",
                        price = 330_000L,
                        discountPercent = 25,
                        image = R.drawable.n4,
                        rating = 4.9f,
                    ),

                    ),
            ),
            Category(
                id = 6,
                title = "پسرانه",
                icon = R.drawable.category_tshirt,
                products = listOf(
                    Product(
                        id = 61,
                        title = "تیشرت پسرانه سفید طرح\u200Cدار",
                        price = 230_000L,
                        discountPercent = 15,
                        image = R.drawable.p1,
                        rating = 4.4f,
                    ),
                    Product(
                        id = 62,
                        title = "ست پسرانه مشکی سفید",
                        price = 350_000L,
                        discountPercent = 12,
                        image = R.drawable.p2,
                        rating = 4.6f,
                    ),

                    ),
                bestProduct = listOf(
                    Product(
                        id = 63,
                        title = "تیشرت و شلوارک پسرانه",
                        price = 360_000L,
                        discountPercent = 25,
                        image = R.drawable.p5,
                        rating = 4.7f,
                    ),
                )
            )
        )
    }
}
