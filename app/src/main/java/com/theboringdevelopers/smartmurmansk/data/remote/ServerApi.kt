package com.theboringdevelopers.smartmurmansk.data.remote

import com.theboringdevelopers.smartmurmansk.data.model.bean.Team
import com.theboringdevelopers.smartmurmansk.data.model.bean.UserInfo
import com.theboringdevelopers.smartmurmansk.data.model.request.CreateTeamRequest
import com.theboringdevelopers.smartmurmansk.data.model.request.RegistrationConfirmationRequest
import com.theboringdevelopers.smartmurmansk.data.model.request.UpdateUserRequest
import com.theboringdevelopers.smartmurmansk.data.model.response.AuthResponse
import com.theboringdevelopers.smartmurmansk.data.model.response.RegistrationResponse
import com.theboringdevelopers.smartmurmansk.data.model.response.TeamResponse
import com.theboringdevelopers.smartmurmansk.data.model.response.UserResponse
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ServerApi {

    @FormUrlEncoded
    @POST("oauth/token")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
    ): Deferred<Response<AuthResponse>>

    @GET("registration/{phone}")
    fun registration(
        @Path("phone") phone: String
    ): Deferred<Response<RegistrationResponse>>

    @POST("registration/confirm")
    fun registrationConfirm(
        @Body registrationConfirmationRequest: RegistrationConfirmationRequest
    ): Deferred<Response<ResponseBody>>

    @PUT("/secured/user")
    fun updateUser(
        @Body updateUserRequest: UpdateUserRequest
    ): Deferred<Response<ResponseBody>>

    @POST("secured/team")
    fun createTeam(
        @Body createTeamRequest: CreateTeamRequest
    ): Deferred<Response<ResponseBody>>

    @GET("secured/user/list")
    fun users(
    ): Deferred<Response<List<UserResponse>>>

    @GET("secured/user")
    fun user(
    ): Deferred<Response<UserResponse>>

    @GET("secured/user")
    fun userById(
        @Query ("userId") id: Long
    ): Deferred<Response<UserResponse>>

    @GET("/secured/team/list")
    fun teams(
    ): Deferred<Response<List<TeamResponse>>>
}