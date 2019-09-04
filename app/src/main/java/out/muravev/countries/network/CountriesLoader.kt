package out.muravev.countries.network

import android.util.Log
import out.muravev.countries.data.SearchCountryModel
import out.muravev.countries.pojos.CountryListPOJO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesLoader(private val model: SearchCountryModel) {

    private var countriesList = arrayListOf<CountryListPOJO>()

    fun loadData(countryName: String) {
        countriesList.clear()
            val call = NetworkService().initClient().getCountryByName(countryName)
            call.enqueue(object : Callback<List<CountryListPOJO>> {

                override fun onResponse(call: Call<List<CountryListPOJO>>, response: Response<List<CountryListPOJO>>) {
                    if (response.body()?.isNotEmpty() ?: throw NullPointerException()) {
                        countriesList.addAll(response.body() ?: throw NullPointerException())
                        model.countriesListLoaded()
                        Log.d("M_CountriesLoader", "kek")
                    } else {
                        model.countriesListLoaded()
                        Log.d("M_CountriesLoader", "${response.body()}")
                    }
                }

                override fun onFailure(call: Call<List<CountryListPOJO>>, t: Throwable) {
                    Log.d("M_CountriesLoader", t.toString())
                }
            })
    }

    fun getData(): List<CountryListPOJO> {
        return countriesList
    }
}