package com.alorma.tempcontacts.data.cache

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME}")
    fun get(): LiveData<List<ContactEntity>>

    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME}")
    fun getList(): List<ContactEntity>

    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME} WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): LiveData<ContactEntity>

    @Query("SELECT * FROM ${ContactEntity.TABLE_NAME} WHERE id LIKE :id LIMIT 1")
    fun getById(id: String): ContactEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg contactEntity: ContactEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg contactEntity: ContactEntity)

    @Query("DELETE FROM ${ContactEntity.TABLE_NAME} WHERE id = :id")
    fun delete(id: String)
}