package ir.net_box.tvshowvideo.presentation.video

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.net_box.tvshowvideo.domain.repository.TechnologyRepository
import ir.net_box.tvshowvideo.presentation.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: TechnologyRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _videoState = mutableStateOf(VideoState())
    val videoState: State<VideoState> = _videoState

    init {
        viewModelScope.launch {
            _videoState.value = videoState.value.copy(isLoading = true)
            savedStateHandle.get<Int>("id")?.let {
                val video = async { repository.getTechnologyById(it) }
                when(val result =video.await()){
                    is Resource.Loading-> _videoState.value = videoState.value.copy(isLoading = result.isLoading)
                    is Resource.Success-> _videoState.value = videoState.value.copy(video = result.data, isLoading = false)
                    is Resource.Error-> _videoState.value = videoState.value.copy(video = null, isLoading = false, errorMessage = result.message)
                }
            }
        }

    }


}