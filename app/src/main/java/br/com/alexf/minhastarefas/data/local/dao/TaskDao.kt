package br.com.alexf.minhastarefas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alexf.minhastarefas.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun findAll(): Flow<List<TaskEntity>>

    @Query("UPDATE tasks SET isDone = 1 WHERE id = :id")
    fun markTaskAsDone(id: String)

    @Query("UPDATE tasks SET isDone = 0 WHERE id = :id")
    fun markTaskAsUndone(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(task: TaskEntity)
}