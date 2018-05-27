package com.alorma.contactnotes.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContactEntity.TABLE_NAME)
data class ContactEntity(
        @PrimaryKey val id: Long,
        @ColumnInfo(name = "androidId") val androidId: String? = null,
        @ColumnInfo(name = "name") val name: String) {

    companion object {
        const val TABLE_NAME = "contacts"
    }
}