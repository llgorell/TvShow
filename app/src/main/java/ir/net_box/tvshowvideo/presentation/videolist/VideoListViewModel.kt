package ir.net_box.tvshowvideo.presentation.videolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.net_box.tvshowvideo.data.local.datastore.AuthToken
import ir.net_box.tvshowvideo.data.remote.TechnologyApi
import ir.net_box.tvshowvideo.data.remote.interceptor.TokenInterceptor
import ir.net_box.tvshowvideo.domain.repository.TechnologyRepository
import ir.net_box.tvshowvideo.presentation.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val repository: TechnologyRepository,
    private val authToken: AuthToken
) : ViewModel() {

    private val _tvShowsState = MutableStateFlow(TVShowState())
    val tvShowsState: StateFlow<TVShowState> = _tvShowsState

    init {
        setToken()
        viewModelScope.launch {
            repository.getTechnologyList(false)
                .catch {  }
                .collect { result->
                    when(result){
                        is Resource.Loading-> {
                            _tvShowsState.value = tvShowsState.value.copy(loading = result.isLoading)
                        }
                        is Resource.Success-> {
                            _tvShowsState.value = tvShowsState.value.copy(data = result.data, loading = false)
                        }
                        is Resource.Error-> {
                            _tvShowsState.value = tvShowsState.value.copy(errorMessage = result.message, data = null, loading = false)
                        }
                    }
                }
        }
    }

    //just for use datastore
    private fun setToken(){
        viewModelScope.launch(Dispatchers.IO) {
            authToken.saveToken(TechnologyApi.API_KEY)
        }
    }

}