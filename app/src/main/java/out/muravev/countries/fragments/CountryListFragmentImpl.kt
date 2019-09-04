package out.muravev.countries.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.country_search_fragment.*
import out.muravev.countries.R
import out.muravev.countries.adapters.CountriesListAdapter
import out.muravev.countries.contracts.SearchViewPresenter
import out.muravev.countries.contracts.SearchViewQueryTextCallback
import out.muravev.countries.data.CountriesApplication
import out.muravev.countries.data.CountryMainInfo
import out.muravev.countries.presenters.SearchCountryPresenterImpl
import out.muravev.countries.tools.DeviceChecker
import out.muravev.countries.tools.SearchViewQueryTextListener

class CountryListFragmentImpl : Fragment(), SearchViewPresenter.CountryListFragment {

    private lateinit var presenter: SearchViewPresenter.SearchCountryPresenter
    private var isLargeDevice = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupTopPanel()
        return inflater.inflate(R.layout.country_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SearchCountryPresenterImpl(
            this,
            (activity?.application as CountriesApplication).countriesModel
        )
        setupListViewItems()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val listener = SearchViewQueryTextListener(object : SearchViewQueryTextCallback {
            override fun onQueryTextChange(query: String) {
                if (query.isNotEmpty()) {
                    presenter.onSearchQuerySent(query)
                } else {
                    presenter.onSearchQueryEmpty()
                }
            }
        })
        val searchAction = menu.findItem(R.id.search_action).actionView as SearchView
        searchAction.setOnQueryTextListener(listener)
    }


    override fun showListView(countriesList: List<CountryMainInfo>) {
        updateListView(countriesList)
    }

    override fun clearListView(emptyList: List<CountryMainInfo>) {
        updateListView(emptyList)
    }

    override fun notFoundMessageVisibility(visibility: Boolean) {
        not_fount_country_text.isVisible = visibility
        not_fount_country_text.text = "Not Found!!!"
    }

    override fun listViewVisibility(visibility: Boolean) {
        country_list_recycler.isVisible = visibility
    }

    override fun progressBarVisibility(visibility: Boolean) {
        waiting_response_progress_bar.isVisible = visibility
    }

    private fun setupTopPanel() {
        isLargeDevice = DeviceChecker(activity?.applicationContext ?: throw NullPointerException()).isDeviceTablet()
        if (!isLargeDevice) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
    }

    private fun setupListViewItems() {
        country_list_recycler.layoutManager = LinearLayoutManager(activity)
        updateListView(emptyList())
    }

    private fun updateListView(newList: List<CountryMainInfo>) {
        country_list_recycler.adapter = CountriesListAdapter(newList)
    }
}