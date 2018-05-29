package com.alorma.tempcontacts.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContactEntity.TABLE_NAME)
data class ContactEntity(
        @PrimaryKey val id: Long? = null,
        @ColumnInfo(name = "androidId") val androidId: String,
        @ColumnInfo(name = "name") val name: String) {

    companion object {
        const val TABLE_NAME = "contacts"
    }
}