package info.alirezaahmadi.animatedshop.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import info.alirezaahmadi.animatedshop.data.model.Category
import info.alirezaahmadi.animatedshop.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) {



}