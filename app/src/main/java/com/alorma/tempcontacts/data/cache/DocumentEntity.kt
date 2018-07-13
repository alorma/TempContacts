package com.alorma.tempcontacts.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DocumentEntity.TABLE_NAME)
data class DocumentEntity(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "type") val type: String,
        @ColumnInfo(name = "deleteTime") val time: Long) {

    companion object {
        const val TABLE_NAME = "documents"
    }
}