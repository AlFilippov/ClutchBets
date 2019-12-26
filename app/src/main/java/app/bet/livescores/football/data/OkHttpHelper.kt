package app.bet.livescores.football.data

import android.util.Log
import app.bet.livescores.football.BuildConfig
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class OkHttpHelper(

) {

    companion object {
        private const val CONNECTION_TIMEOUT = 30L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
    }

    fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ConnectionInterceptor())

        return builder.build()
    }

    fun createOkHttpClientString(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ConnectionInterceptor())
            .addInterceptor(AgentInterceptor())
        return builder.build()
    }

    class AgentInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val agent = System.getProperty("http.agent")
            val original = chain.request()
            val request = original.newBuilder()
            request.addHeader("User-Agent", agent)
            return chain.proceed(request.build())
        }

    }

    class ConnectionInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            try {
                val original = chain.request()
                val builder = original.newBuilder()
                val response = chain.proceed(builder.build())

                val responseCode = response.code()
                if (responseCode < HttpURLConnection.HTTP_OK || responseCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                    if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
                        throw AuthException()
                    }

                    val responseBody = response.body() ?: throw ServerErrorException(responseCode)

                    val bodyString = responseBody.string()
                    try {
                        val serverError = Gson().fromJson(bodyString, ServerError::class.java)
                        val errorMessage = serverError.message
                        if (errorMessage != null) {
                            throw ServerErrorException(responseCode, bodyString, errorMessage)
                        }
                    } catch (e: JsonSyntaxException) {

                    }
                    throw ServerErrorException(responseCode, bodyString)
                }
                return response
            } catch (e: SocketTimeoutException) {
                throw e
            } catch (e: UnknownHostException) {
                throw e
            } catch (e: SocketException) {
                throw e
            }
        }
    }
}