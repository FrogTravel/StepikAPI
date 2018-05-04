package nekono.inno.stepikapi

import io.reactivex.Observable
import retrofit2.http.GET

interface API{
    @GET("https://stepik.org/api/search-results?query=kotlin")
    fun getSearchResults(): Observable<Course>
}