package out.muravev.countries.data

import out.muravev.countries.contracts.CountryListeners
import out.muravev.countries.network.CountriesLoader
import out.muravev.countries.pojos.CountryListPOJO
import java.lang.ref.WeakReference

class SearchCountryModel {

    private var countriesList = arrayListOf<CountryListPOJO>()
    private var countryName = ""
    private val countriesLoader = CountriesLoader(this)
    private var listListeners = arrayListOf<WeakReference<CountryListeners>>()
//    private var progressBarVisibility = false
//    private var countriesListVisibility = false
//    private var notFoundMessageVisibility = false

    fun setListener(listener: CountryListeners) {
        listListeners.add(WeakReference(listener))
    }

    fun removeListener(listener: CountryListeners) {
        for (i in 0..listListeners.lastIndex) {
            if (listListeners[i].get() == listener) {
                listListeners.removeAt(i)
                break
            }
        }
    }

    fun setSearchQuery(country: String) {
        countryName = country
    }

    fun loadCountries() {
        countriesLoader.loadData(countryName)
    }

    fun getEmptyList() =
        emptyList<CountryMainInfo>()

    fun getCountries(): List<CountryMainInfo> {
        val countriesInfo = arrayListOf<CountryMainInfo>()
        for (i in 0..countriesList.lastIndex) {
            countriesInfo.add(
                CountryMainInfo(
                    countriesList[i].name,
                    countriesList[i].population,
                    countriesList[i].capital
                )
            )
        }
        return countriesInfo
    }

    fun countriesListLoaded() {
        countriesList.clear()
        countriesList.addAll(countriesLoader.getData())
        for (i in 0..listListeners.lastIndex)
            if (listListeners[i].get() != null)
                listListeners[i].get()?.onDataChanged()
    }

//    fun setProgressBarVisibility(visibility: Boolean) {
//        progressBarVisibility = visibility
//    }
//
//    fun setCountriesListVisibility(visibility: Boolean) {
//        countriesListVisibility = visibility
//    }
//
//    fun setNotFoundMessageVisibility(visibility: Boolean) {
//        notFoundMessageVisibility = visibility
//    }
//
//    fun getProgressBarVisibility() =
//        progressBarVisibility
//
//    fun getCountriesListVisibility() =
//        countriesListVisibility
//
//    fun getNotFoundMessageVisibility() =
//        notFoundMessageVisibility
}