package com.example.tokomandiri.app.login.data

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.login.data.remote.FakeStoreLoginApi
import com.example.tokomandiri.app.login.data.remote.model.LoginRequest
import com.example.tokomandiri.app.login.data.remote.model.UserLoginDto

class LoginRepositoryImpl(private val api: FakeStoreLoginApi) : LoginRepository {
    override suspend fun login(username: String, password: String) : ApiResponse<UserLoginDto?>{
        val tokenRequest = api.login(LoginRequest(username, password))

//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//        val json = moshi.adapter(LoginRequest::class.java)
//            .toJson(LoginRequest(username = "m38rmF$", password = "johnd"))
//        Log.d("WLDN Debug", json)

        if(tokenRequest.isSuccessful){
            val userDataList = api.getUsers()
            val userObject = userDataList.body()?.find { it.username == username }
            val token = tokenRequest.body()?.token.orEmpty()

            if(userObject != null && token.isNotBlank()){
                val user = UserLoginDto(
                    user = userObject,
                    token = token
                )
                return ApiResponse.Success(user)
            }else{
                if(token.isBlank()) {
                    return ApiResponse.Error("Invalid Login Credential")
                }
                if(userObject == null){
                    return ApiResponse.Error("User Data not Found")
                }
            }
        }else{
            return ApiResponse.Error("Invalid Login Credential")
        }
        return return ApiResponse.Error("Unknown Error")
    }

}