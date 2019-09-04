package out.muravev.countries.pojos

import com.google.gson.annotations.SerializedName

class CountryListPOJO (

    @SerializedName("name")
    val name: String,

    @SerializedName("capital")
    val capital: String,

    @SerializedName("population")
    val population: Int
)