package out.muravev.countries.tools

import android.widget.SearchView
import out.muravev.countries.contracts.SearchViewQueryTextCallback

class SearchViewQueryTextListener(private val callback: SearchViewQueryTextCallback) : SearchView.OnQueryTextListener {

    override fun onQueryTextChange(query: String): Boolean {
        callback.onQueryTextChange(query)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

}