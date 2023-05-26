package com.app.fetchTestApp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.fetchTestApp.adapter.models.EventItem
import com.app.fetchTestApp.adapter.models.HeaderItem
import com.app.fetchTestApp.adapter.models.ListItem
import com.app.fetchTestApp.utils.Constants
import com.app.network_module.models.response.DataResponse
import com.app.network_module.repository.ApiRepository
import com.app.network_module.repository.onError
import com.app.network_module.repository.onSuccess
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

open class MainActivityViewModel : BaseViewModel() {

    var _responseData = MutableLiveData<ArrayList<ListItem>>()
    val responseData: LiveData<ArrayList<ListItem>>
        get() = _responseData

    /*API SECTION*/

    fun getData() {
        coroutineScope.launch {
            toggleLoader(true)
            val data = ApiRepository.callGetData()
            data.onSuccess {
                toggleLoader(false)
                if (it.isSuccessful && it.code() == Constants.API_RESPONSE_CODE_200) {
                    val groups: TreeMap<Int?, MutableList<DataResponse>>? =
                        it.body()?.groupByTo(TreeMap()) { item -> item.listId }
                    val items = ArrayList<ListItem>()
                    groups?.keys?.let { keys ->
                        for (id in keys) {
                            val header = HeaderItem(id)
                            items.add(header)
                            groups[id]?.filter { !it.name.isNullOrBlank() }
                                ?.sortedWith(compareBy({ it.name?.length }, { it.name }))
                                .let { data ->
                                    if (data != null) {
                                        for (event in data) {
                                            val item = EventItem(event)
                                            items.add(item)

                                        }
                                    }
                                }
                        }
                    }
                    _responseData.postValue(items)
                } else if (it.code() == Constants.API_RESPONSE_CODE_401) {
                    //Auth error or logout app
                    handleServerError(it.message())
                } else if (it.code() == Constants.API_RESPONSE_CODE_500) {
                    handleServerError(it.message())
                } else {
                    handleServerError(it.message())
                }
            }.onError {
                toggleLoader(false)
                showErrorMessage(it.exception)
            }
        }
    }


}