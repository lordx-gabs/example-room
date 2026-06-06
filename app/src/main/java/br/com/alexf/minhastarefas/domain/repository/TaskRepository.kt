package br.com.alexf.minhastarefas.domain.repository

import br.com.alexf.minhastarefas.data.local.entity.TaskEntity
import br.com.alexf.minhastarefas.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    val tasks: Flow<List<TaskEntity>>
    suspend fun save(task: Task)
    suspend fun completeTask(id: String)
    suspend fun reopenTask(id: String)
}