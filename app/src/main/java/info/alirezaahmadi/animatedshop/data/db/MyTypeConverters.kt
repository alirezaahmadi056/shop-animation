package info.alirezaahmadi.animatedshop.data.db

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MyTypeConverters {
    @TypeConverter
    fun convertListToString(feature: List<String>): String {
        return Json.encodeToString(feature)
    }

    @TypeConverter
    fun decodeStringToList(feature: String): List<String> {
        return try {
            Json.decodeFromString<List<String>>(feature)
        }catch (e:Exception){
            emptyList()
        }
    }

}