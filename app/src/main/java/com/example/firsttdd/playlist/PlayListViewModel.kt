package com.example.firsttdd.playlist

import androidx.lifecycle.*
import com.example.firsttdd.recylerviewdata.PlayListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val repository: PlayListRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playLists = MutableLiveData<Result<List<PlayListItem>>>()
//    val playLists = liveData<Result<List<PlayListItem>>> {
//        loader.postValue(true)
//        emitSource(repository.getPlayLists()
//            .onEach {
//                loader.value=false
//
//            }
//            .asLiveData())
//    }

//    val playLists: LiveData<List<PlayListItem>> = liveData {
//        val data = repository.getPlayLists() // loadUser is a suspend function.
//        data.collect{
//            if (it.isSuccess){
//                emit(it.getOrNull()!!)
//            }
//        }
//
//    }

    init {
        viewModelScope.launch {
            repository.getPlayLists().collect {
                playLists.value = it
            }

        }
    }
}
