package info.alirezaahmadi.animatedshop.util

import java.text.DecimalFormat


fun String.byLocate(): String {
    var result = ""
    var fa = '۰'
    for (ch in this) {
        fa = ch
        when (ch) {
            '0' -> fa = '۰'
            '1' -> fa = '۱'
            '2' -> fa = '۲'
            '3' -> fa = '۳'
            '4' -> fa = '۴'
            '5' -> fa = '۵'
            '6' -> fa = '۶'
            '7' -> fa = '۷'
            '8' -> fa = '۸'
            '9' -> fa = '۹'
        }
        result = "${result}$fa"
    }
    return result
}

fun String.bySeparator(): String {
    val priceFormat = DecimalFormat("###,###")
    return priceFormat.format(Integer.valueOf(this))
}

fun String.byLocateAndSeparator(): String {
    val priceWithoutCommas = this.replace(",", "")
    val persianDigit = priceWithoutCommas.bySeparator()
    return persianDigit.byLocate()
}
