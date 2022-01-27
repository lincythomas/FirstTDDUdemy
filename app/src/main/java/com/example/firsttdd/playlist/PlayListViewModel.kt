package com.example.firsttdd.playlist

import androidx.lifecycle.*
import com.example.firsttdd.recylerviewdata.PlayListItem
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val repository: PlayListRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    //    val playLists = MutableLiveData<Result<List<PlayListItem>>>()
    val playLists = liveData<Result<List<PlayListItem>>> {
        loader.postValue(true)
        emitSource(repository.getPlayLists()
            .onCompletion {
                loader.value=false
            }
            .asLiveData())
    }

    /*init {
        viewModelScope.launch {
            repository.getPlayLists().collect {
                playLists.value = it
            }

        }
    }*/
}
