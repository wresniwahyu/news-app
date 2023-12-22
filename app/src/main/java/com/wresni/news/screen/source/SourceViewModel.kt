package com.wresni.news.screen.source

import androidx.lifecycle.viewModelScope
import com.wresni.news.base.BaseViewModel
import com.wresni.news.data.Repository
import com.wresni.news.data.model.SourceUiModel
import com.wresni.news.di.IoDispatcher
import com.wresni.news.util.onError
import com.wresni.news.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    @IoDispatcher
    private val dispatcherIO: CoroutineDispatcher,
    private val repository: Repository
): BaseViewModel<SourceViewModel.State, SourceViewModel.Event>() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {
        getSources()
    }

    fun getSources() = viewModelScope.launch {
        _state.update { state -> state.copy(isLoading = true) }
        repository.getSources()
            .onSuccess {
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        sources = it
                    )
                }
            }.onError { e, code, message, error ->
                _state.update { state -> state.copy(isLoading = false) }
                _event.emit(Event.ShowMessage(message ?: "Something went wrong!"))
            }
    }

    override fun defaultState() = State(
        isLoading = false,
        sources = emptyList()
    )

    override fun onEvent(event: Event) {
        viewModelScope.launch(dispatcherIO) {
            when (event) {
                is Event.ShowMessage -> _event.emit(Event.ShowMessage(event.message))
            }
        }
    }

    data class State(
        val isLoading: Boolean,
        val sources: List<SourceUiModel>
    )

    sealed class Event {
        class ShowMessage(val message: String): Event()
    }

}