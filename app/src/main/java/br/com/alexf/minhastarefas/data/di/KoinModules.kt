package br.com.alexf.minhastarefas.data.di

import br.com.alexf.minhastarefas.data.repository.TasksRepository
import br.com.alexf.minhastarefas.ui.feature.taskform.TaskFormViewModel
import br.com.alexf.minhastarefas.ui.feature.taskslist.TasksListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val appModules = module {
    viewModel { TaskFormViewModel(get()) }
    viewModel { TasksListViewModel(get()) }
}

val storageModule = module {
    singleOf(::TasksRepository)
}