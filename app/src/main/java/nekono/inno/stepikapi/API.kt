package nekono.inno.stepikapi

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


interface API{
    @GET("/api/search-results")
    fun getSearchResults( @Query("query") search : String, @Query("page") page : Int) : Observable<Reply>

    companion object {

        fun create(): API{
            val okHttpClient = OkHttpClient().newBuilder()
            okHttpClient
                    .writeTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS)
                    .connectTimeout(3, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://stepik.org")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(API :: class.java)
        }

    }
}