package com.wresni.news.screen.all

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.wresni.news.base.BaseViewModel
import com.wresni.news.data.Repository
import com.wresni.news.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNewsViewModel @Inject constructor(
    @IoDispatcher
    private val dispatcherIO: CoroutineDispatcher,
    private val repository: Repository
) : BaseViewModel<AllNewsViewModel.State, AllNewsViewModel.Event>() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun getNews(keyword: String = "") = repository.getAllNews(keyword).cachedIn(viewModelScope)

    override fun defaultState() = State(
        isLoading = false
    )

    override fun onEvent(event: Event) {
        viewModelScope.launch(dispatcherIO) {
            when (event) {
                is Event.ShowMessage -> _event.emit(Event.ShowMessage(event.message))
            }
        }
    }

    data class State(
        val isLoading: Boolean
    )

    sealed class Event {
        class ShowMessage(val message: String) : Event()
    }
}