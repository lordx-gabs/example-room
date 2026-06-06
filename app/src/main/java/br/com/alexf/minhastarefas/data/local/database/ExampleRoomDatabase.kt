package br.com.alexf.minhastarefas.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alexf.minhastarefas.data.local.dao.TaskDao
import br.com.alexf.minhastarefas.data.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class ExampleRoomDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}