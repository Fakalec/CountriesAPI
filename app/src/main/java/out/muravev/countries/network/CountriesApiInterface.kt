package out.muravev.countries.network

import out.muravev.countries.pojos.CountryListPOJO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CountriesApiInterface {

    @GET("name/{country}")
    fun getCountryByName(@Path("country") name: String): Call<List<CountryListPOJO>>
}