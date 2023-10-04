package com.mcmouse88.mvvm_news_app.data.local.converters

import androidx.room.TypeConverter
import com.mcmouse88.mvvm_news_app.data.local.entities.SourceType

class Converters {

    @TypeConverter
    fun fromSource(source: SourceType): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): SourceType {
        return SourceType(name, name)
    }
}