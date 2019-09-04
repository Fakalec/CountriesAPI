package out.muravev.countries.contracts

import out.muravev.countries.data.CountryMainInfo

interface SearchViewPresenter {

    interface CountryListFragment {
        fun showListView(countriesList: List<CountryMainInfo>)
        fun clearListView(emptyList: List<CountryMainInfo>)
        fun listViewVisibility(visibility: Boolean)
        fun notFoundMessageVisibility(visibility: Boolean)
        fun progressBarVisibility(visibility: Boolean)
    }

    interface SearchCountryPresenter {
        fun onSearchQuerySent(query: String)
        fun onSearchQueryEmpty()
    }
}
