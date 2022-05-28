package com.thecode007.gitgooglerpo.data.remote

import com.thecode007.gitgooglerpo.data.remote.dto.RepoDTO
import com.thecode007.gitgooglerpo.ui.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

@Suppress("BlockingMethodInNonBlockingContext")
class ApiFlowWrapper {

    operator fun invoke(apiCallBack:suspend () -> List<RepoDTO>): Flow<UiModel> {
        return flow {
            try {
                emit(UiModel.Loading)
                val resultObject = apiCallBack()
                emit(UiModel.Success(resultObject))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                var message = ""
                if (errorBody != null) {
                    message =  errorBody.byteString().utf8()
                    if (message.isNotEmpty())
                        emit(UiModel.Error(message))
                }
                if (message.isNotEmpty()) {
                    emit(UiModel.Error(message))
                }
            } catch (e: IOException) {
                emit(UiModel.Error(message = "Couldn't reach server. Check your internet connection."))
            }
        }
    }
}