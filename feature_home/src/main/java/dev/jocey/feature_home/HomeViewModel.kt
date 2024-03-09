package dev.jocey.feature_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jocey.domain.use_case.GetAllNumbersUseCase
import dev.jocey.domain.use_case.GetNumberUseCase
import dev.jocey.domain.use_case.GetRandomNumber
import dev.jocey.feature_home.mapper.NumberMapper
import dev.jocey.feature_home.model.NumberView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getAllNumbersUseCase: GetAllNumbersUseCase,
    private val getNumberUseCase: GetNumberUseCase,
    private val getRandomNumber: GetRandomNumber,
    private val numberMapper: NumberMapper
) : ViewModel() {
    init {
        getAllNumbers()
    }

    private val _allNumbers: MutableLiveData<List<NumberView>> = MutableLiveData<List<NumberView>>()
    val allNumbers: LiveData<List<NumberView>> = _allNumbers

    private val _numberLiveData: MutableLiveData<UiHomeState> = MutableLiveData<UiHomeState>()
    val numberLiveData: LiveData<UiHomeState> = _numberLiveData

    private fun getAllNumbers() {
        viewModelScope.launch {
            getAllNumbersUseCase()
                .map { it.map { numberDomain -> numberMapper.mapFromDomainToView(numberDomain) } }
                .collect {
                    _allNumbers.value = it
                }
        }
    }

    fun getNumber(nextInt: String) {
        viewModelScope.launch(myExceptionHandler) {
            val num = getNumberUseCase(nextInt)
            _numberLiveData.value = UiHomeState.Success(num)
        }
    }

    fun getRandomFact() {
        viewModelScope.launch(myExceptionHandler) {
            getRandomNumber()
        }
    }

    private val myExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _numberLiveData.value = UiHomeState.Error(throwable)
    }
}

sealed class UiHomeState {
    data class Success<T>(val value: T) : UiHomeState()
    data class Error(val exception: Throwable) : UiHomeState()
}