package com.theboringdevelopers.smartmurmansk.data.repository

import android.content.Context
import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import com.theboringdevelopers.smartmurmansk.data.model.bean.Team
import com.theboringdevelopers.smartmurmansk.data.model.request.CreateTeamRequest
import com.theboringdevelopers.smartmurmansk.data.model.response.TeamResponse
import com.theboringdevelopers.smartmurmansk.data.model.response.UserResponse
import com.theboringdevelopers.smartmurmansk.data.remote.ServerApi
import com.theboringdevelopers.smartmurmansk.util.helpers.HttpUtils.safeApiCall
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import javax.inject.Inject

class TeamRepository  @Inject constructor(
    private val serverApi: ServerApi,
    @ApplicationContext private val context: Context
) {

    suspend fun create(
        userId: Long,
        team: Team,
        sportTypes: List<SportType>
    ) : ResponseBody {
        return safeApiCall {
            serverApi.createTeam(CreateTeamRequest(
                userId = userId,
                team = team,
                sportTypes = sportTypes
            )).await()
        }.data
    }

    suspend fun users(
        error: (String) -> Unit
    ) : List<UserResponse> {
        return try {
            val result = safeApiCall {
                serverApi.users().await()
            }.data
            result
        } catch (th: Throwable) {
            error(th.message.toString())
            emptyList()
        }
    }

    suspend fun teams(
        error: (String) -> Unit
    ) : List<TeamResponse> {
        return try {
            val result = safeApiCall {
                serverApi.teams(
                    true
                ).await()
            }.data
            result
        } catch (th: Throwable) {
            error(th.message.toString())
            emptyList()
        }
    }
}