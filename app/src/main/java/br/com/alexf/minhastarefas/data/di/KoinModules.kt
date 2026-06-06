package br.com.alexf.minhastarefas.data.di

import androidx.room.Room
import br.com.alexf.minhastarefas.data.local.database.ExampleRoomDatabase
import br.com.alexf.minhastarefas.data.repository.TaskRepositoryImpl
import br.com.alexf.minhastarefas.domain.repository.TaskRepository
import br.com.alexf.minhastarefas.ui.feature.taskform.TaskFormViewModel
import br.com.alexf.minhastarefas.ui.feature.taskslist.TasksListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModel { TaskFormViewModel(get()) }
    viewModel { TasksListViewModel(get()) }
}

val storageModule = module {
    singleOf(::TaskRepositoryImpl) bind TaskRepository::class
    single {
        Room.databaseBuilder(
            androidContext(),
            ExampleRoomDatabase::class.java,
            "example-room-database"
        ).build()
    }
    single {
        get<ExampleRoomDatabase>().taskDao()
    }
}