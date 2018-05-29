package com.alorma.tempcontacts.data.cache

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface ContactDao {
    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME}")
    fun get(): Maybe<List<ContactEntity>>

    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME} WHERE id LIKE :id LIMIT 1")
    fun findById(id: Long): Maybe<ContactEntity>

    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME} WHERE androidId LIKE :id LIMIT 1")
    fun findByAndroidId(id: String): Maybe<ContactEntity>

    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME} WHERE androidId LIKE :id LIMIT 1")
    fun getByAndroidId(id: String): ContactEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg contactEntity: ContactEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg contactEntity: ContactEntity)

    @Delete
    fun delete(user: ContactEntity)
}