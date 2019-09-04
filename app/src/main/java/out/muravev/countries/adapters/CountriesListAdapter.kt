package out.muravev.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
//import android.widget.Filter
//import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import out.muravev.countries.R
import out.muravev.countries.data.CountryMainInfo

class CountriesListAdapter(private var countriesList: List<CountryMainInfo>) :
    RecyclerView.Adapter<CountriesListViewHolder>() {

//    private lateinit var countriesListFiltered: List<CountryMainInfo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListViewHolder {
        return CountriesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler, parent, false)
        )
    }

    override fun getItemCount() =
        countriesList.size

    override fun onBindViewHolder(holder: CountriesListViewHolder, position: Int) {

        val country = countriesList[position]

        holder.countryName.text = country.name
        holder.countryCapital.text = country.capital
        holder.countryPopulation.text = country.population.toString()
    }
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence): FilterResults {
//                val charString = charSequence.toString()
//                if (charString.isEmpty()) {
//                    countriesListFiltered = countriesList
//                } else {
//                    val filteredList = arrayListOf<CountryMainInfo>()
//                    for (row in countriesList) {
//                        if (row.name.toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row)
//                        }
//                    }
//                    countriesListFiltered = filteredList
//                }
//
//                val filterResults = FilterResults()
//                filterResults.values = countriesListFiltered
//                return filterResults
//            }
//
//            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
//                @Suppress("UNCHECKED_CAST")
//                countriesListFiltered = filterResults.values as ArrayList<CountryMainInfo>
//                notifyDataSetChanged()
//            }
//        }
//    }
}