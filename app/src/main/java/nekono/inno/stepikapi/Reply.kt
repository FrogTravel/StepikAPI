package nekono.inno.stepikapi

import com.google.gson.annotations.SerializedName

class Reply(@SerializedName("search-results") val search_results: ArrayList<Course>, val meta : Meta)