package dev.jocey.listdetail.temp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jocey.domain.use_case.GetNumberUseCase
import dev.jocey.listdetail.temp.mapper.NumberMapper
import dev.jocey.listdetail.temp.model.NumberView
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getNumberUseCase: GetNumberUseCase,
    private val numberMapper: NumberMapper
) : ViewModel() {

    private val _numberLiveData: MutableLiveData<NumberView> = MutableLiveData<NumberView>()
    val numberLiveData: LiveData<NumberView> = _numberLiveData

    fun getNumber(nextInt: Int) {
        viewModelScope.launch {
            val num = getNumberUseCase.invoke(nextInt.toString())
            _numberLiveData.value = numberMapper.mapFromDomainToView(num)
        }
    }
}