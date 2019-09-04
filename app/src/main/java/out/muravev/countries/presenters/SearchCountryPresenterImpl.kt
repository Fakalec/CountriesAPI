package out.muravev.countries.presenters

import out.muravev.countries.contracts.CountryListeners
import out.muravev.countries.contracts.SearchViewPresenter
import out.muravev.countries.data.SearchCountryModel

class SearchCountryPresenterImpl(
    private val view: SearchViewPresenter.CountryListFragment,
    private val model: SearchCountryModel
) : SearchViewPresenter.SearchCountryPresenter {

    private val listener = object : CountryListeners {
        override fun onDataChanged() {
            if (model.getCountries().isEmpty()) {
                view.clearListView(model.getEmptyList())
                view.listViewVisibility(false)
                view.notFoundMessageVisibility(true)
            } else {
                view.listViewVisibility(true)
                view.notFoundMessageVisibility(false)
                view.showListView(model.getCountries())
            }
            view.progressBarVisibility(false)
        }
    }

    override fun onSearchQueryEmpty() {
        view.showListView(model.getEmptyList())
    }

    override fun onSearchQuerySent(query: String) {
        view.progressBarVisibility(true)
        model.removeListener(listener)
        model.setSearchQuery(query)
        model.loadCountries()
        model.setListener(listener)
    }
}