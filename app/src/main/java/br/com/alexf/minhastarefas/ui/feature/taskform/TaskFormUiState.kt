package br.com.alexf.minhastarefas.ui.feature.taskform

data class TaskFormUiState(
    val title: String = "",
    val description: String = "",
    val topAppBarTitle: String = "",
    val onTitleChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {}
)