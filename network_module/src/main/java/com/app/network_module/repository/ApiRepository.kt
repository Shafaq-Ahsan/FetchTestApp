package com.app.network_module.repository


import com.app.network_module.models.response.DataResponse
import com.app.network_module.utils.Enums
import retrofit2.Response

object ApiRepository {

    private val api = RetrofitBuilder.getRetrofitInstance(Enums.RetrofitBaseUrl.BASE_URL)

    suspend fun callGetData(): Result<Response<List<DataResponse>>> {
        return try {
            Success(api.getData())
        } catch (e: Exception) {
            Error(e)
        }
    }


}