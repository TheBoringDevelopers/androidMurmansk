package com.theboringdevelopers.smartmurmansk.di

import android.content.Context
import android.content.pm.PackageInfo
import android.util.Log
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.theboringdevelopers.smartmurmansk.BuildConfig
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.model.ClientInfoInterceptor
import com.theboringdevelopers.smartmurmansk.data.model.NullOrEmptyConverterFactory
import com.theboringdevelopers.smartmurmansk.data.remote.ServerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    /**
     * Провайдер Retrofit
     */
    @Provides
    @ActivityRetainedScoped
    fun provideRetrofit(@ApplicationContext appContext: Context,
                        userContext: UserContext
    ): Retrofit {

        val pInfo: PackageInfo = appContext.packageManager.getPackageInfo(appContext.packageName, 0)
        val interceptor = ClientInfoInterceptor(applicationId = appContext.packageName, appVersion = pInfo.versionName)
        val loggingInterceptor = HttpLoggingInterceptor() {
            Log.d("SmartMurmansk", it)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(userContext))

        val timeout = appContext.resources.getInteger(R.integer.base_timeout).toLong()
        builder.readTimeout(timeout, TimeUnit.SECONDS)
        builder.writeTimeout(timeout, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(builder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(NullOrEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    /**
     * Провайдер AuthApi
     */
    @Provides
    @ActivityRetainedScoped
    fun provideAuthApi(retrofit: Retrofit) : ServerApi = retrofit.create(ServerApi::class.java)


}

/**
 * Класс для добавления токена авторизации в хидер
 */
class AuthInterceptor(
    /** Токен доступа  */
    private val userContext: UserContext
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request: Request = chain.request()
        if (request.url.encodedPath.contains("secured") && userContext.accessToken != null) {
            request = request.newBuilder()
                .header("Authorization", "Bearer " + userContext.accessToken!!)
                .build()
        }
        return chain.proceed(request)
    }
}