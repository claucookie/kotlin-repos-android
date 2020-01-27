package dev.claucookielabs.kotlinreposapp.common.data.datasource.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object GithubContentServiceFactory {

    private const val BASE_URL = "https://raw.githubusercontent.com"

    fun makeGithubContentService(): GithubContentService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(GithubContentService::class.java)
    }
}
