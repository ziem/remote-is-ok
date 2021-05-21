package com.github.ziem.remoteisok.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JobEntityDao {
    @Query("SELECT * FROM job ORDER BY datetime(date)")
    fun getAll(): Flow<List<JobEntity>>

    @Query("SELECT * FROM job WHERE id = :id LIMIT 1")
    suspend fun find(id: Long): JobEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(jobEntities: List<JobEntity>): List<Long>

    @Delete
    suspend fun delete(jobEntity: JobEntity)
}
