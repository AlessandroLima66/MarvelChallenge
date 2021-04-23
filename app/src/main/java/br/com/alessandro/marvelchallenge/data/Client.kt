package br.com.alessandro.marvelchallenge.data


import br.com.alessandro.marvelchallenge.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class Client {
    fun createClient(currentTime: Long): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor {
                it.proceed(
                    getRequest(it, currentTime)
                )
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

    private fun getRequest(
        chain: Interceptor.Chain,
        currentTimestamp: Long
    ): Request {
        return chain.request()
            .newBuilder()
            .url(getUrl(chain, currentTimestamp))
            .build()
    }

    private fun getUrl(chain: Interceptor.Chain, currentTimestamp: Long): HttpUrl {
        return chain.request().url()
            .newBuilder()
            .addQueryParameter("ts", currentTimestamp.toString())
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
            .addQueryParameter("hash", getHash(currentTimestamp))
            .build()
    }

    private fun getHash(currentTimestamp: Long): String =
        ("${currentTimestamp}${ BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}").toMd5()

    private fun String.toMd5(): String {
        val md5 = MessageDigest.getInstance("MD5")
        val byteArray = this.toByteArray()
        val bigInt = BigInteger(1, md5.digest(byteArray))
        return bigInt.toString(16).padStart(32, '0')
    }
}
