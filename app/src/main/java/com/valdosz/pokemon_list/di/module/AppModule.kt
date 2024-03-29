package com.valdosz.pokemon_list.di.module

import android.annotation.SuppressLint
import android.app.Application
import com.valdosz.pokemon_list.model.api.PokemonAPI
import com.valdosz.pokemon_list.model.repository.PokemonRepository
import com.valdosz.pokemon_list.model.repository.PokemonRepositoryImpl
import com.valdosz.pokemon_list.utils.BASE_URL
import com.valdosz.pokemon_list.utils.TIMEOUT_HTTP
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by ByHil on 24/09/2023
 */

@Module
open class AppModule(private val app: Application) {
    @Provides
    fun app(): Application {
        return app
    }

    private fun provideOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains

            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            var builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { _, _ -> true })

            var loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            return builder.addInterceptor(loggingInterceptor)
                .readTimeout(TIMEOUT_HTTP.toLong(), TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_HTTP.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_HTTP.toLong(), TimeUnit.SECONDS)
                .build()

        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Provides
    open fun pokemonAPI(): PokemonAPI {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(PokemonAPI::class.java)
    }

    @Provides
    open fun pokemonRepository(api: PokemonAPI): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
}