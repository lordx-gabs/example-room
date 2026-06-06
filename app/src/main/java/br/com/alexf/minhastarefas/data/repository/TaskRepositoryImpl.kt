package br.com.alexf.minhastarefas.data.repository

import br.com.alexf.minhastarefas.data.local.dao.TaskDao
import br.com.alexf.minhastarefas.data.local.entity.TaskEntity
import br.com.alexf.minhastarefas.domain.models.Task
import br.com.alexf.minhastarefas.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class TaskRepositoryImpl(
    private val taskDao: TaskDao
): TaskRepository {
    override val tasks get() = taskDao.findAll()

    override suspend fun save(task: Task) = withContext(IO) {
        taskDao.save(task.toTaskEntity())
    }

    override suspend fun completeTask(id: String) = withContext(IO) {
        taskDao.markTaskAsDone(id)
    }

    override suspend fun reopenTask(id: String) = withContext(IO) {
        taskDao.markTaskAsUndone(id)
    }
}

fun Task.toTaskEntity() = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    isDone = this.isDone
)

fun TaskEntity.toTask() = Task(
    id = this.id,
    title = this.title,
    description = this.description,
    isDone = this.isDone
)