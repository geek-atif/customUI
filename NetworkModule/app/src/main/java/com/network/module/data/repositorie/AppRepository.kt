package  com.network.module.data.repositorie

import com.network.module.data.network.ApiService
import com.network.module.data.network.RetrofitInstance
import com.network.module.model.UIModel

/**
 * Created by Atif Qamar
 */

class AppRepository {
    private var service: ApiService = RetrofitInstance.appService
    suspend fun fetchCustomUI(): UIModel = service.fetchCustomUI()
}