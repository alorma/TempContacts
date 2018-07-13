package com.alorma.tempcontacts.data.cache

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DocumentsDao {
    @Query("SELECT * FROM ${DocumentEntity.TABLE_NAME}")
    fun get(): LiveData<List<DocumentEntity>>

    @Query("SELECT * FROM ${DocumentEntity.TABLE_NAME}")
    fun getList(): List<DocumentEntity>

    @Query("SELECT * FROM ${DocumentEntity.TABLE_NAME} WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): LiveData<DocumentEntity>

    @Query("SELECT * FROM ${DocumentEntity.TABLE_NAME} WHERE id LIKE :id LIMIT 1")
    fun getById(id: String): DocumentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg documentEntity: DocumentEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg documentEntity: DocumentEntity)

    @Query("DELETE FROM ${DocumentEntity.TABLE_NAME} WHERE id = :id")
    fun delete(id: String)
}