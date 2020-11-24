package com.app.module.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.app.module.factory.UIEventManager
import com.network.module.data.NetworkData
import com.network.module.data.repositorie.AppRepository
import com.network.module.model.UIModel
import kotlinx.coroutines.Job
import java.io.IOException
import kotlinx.coroutines.launch

class EzetapViewModel(private val eventManager: UIEventManager) : ViewModel(), NetworkData {
    private val repository = AppRepository()

    private val _uIModelData = MutableLiveData<UIModel>()
    val uIModelData: LiveData<UIModel>
        get() = _uIModelData

    init {
        _uIModelData.value
    }

    override fun fetchCustomUI(): Job = viewModelScope.launch {
        try {
            eventManager.showProgressBar()
            _uIModelData.value= repository.fetchCustomUI()
            eventManager.hideProgressBar()
        }
        catch (e: IOException) {
            eventManager.showToast(e.message.toString())
            eventManager.hideProgressBar()
        }
        catch (e: Exception) {
            e.printStackTrace()
            eventManager.showToast("Exception")
            eventManager.hideProgressBar()
        }
    }


}