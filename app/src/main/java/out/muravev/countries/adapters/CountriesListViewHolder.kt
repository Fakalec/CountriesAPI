package out.muravev.countries.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler.view.*

class CountriesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val countryName = view.country_name
    val countryCapital = view.country_capital
    val countryPopulation = view.country_population
}