package com.lomovtsev.customsortfiles

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project


@State(
    name = "FileOrderStorage",
    storages = [Storage("custom-sort-files.xml")]
)
@Service(Service.Level.PROJECT)
class FileOrderStorage(
    private var state: State = State(mutableMapOf(
        "zfirst.txt" to 1,
        "README.md" to -1,
        "r2.json" to -2,
    ))
) : PersistentStateComponent<FileOrderStorage.State> {
    companion object {
        fun getInstance(project: Project): FileOrderStorage {
            return project.getService(FileOrderStorage::class.java)
        }
    }
    
    override fun getState(): State {
        return state
    }

    override fun loadState(state: State) {
        this.state = state
    }

    data class State(
        val customFilePositions: MutableMap<String, Int> = mutableMapOf(),
    )
}
