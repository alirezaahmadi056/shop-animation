package info.alirezaahmadi.animatedshop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.alirezaahmadi.animatedshop.data.model.Category
import info.alirezaahmadi.animatedshop.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
):ViewModel() {

    private val _allCategory:MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val allCategory :StateFlow<List<Category>> =_allCategory.asStateFlow()

    init { viewModelScope.launch(Dispatchers.IO) { _allCategory.emit(repository.getAllCategory()) } }

}