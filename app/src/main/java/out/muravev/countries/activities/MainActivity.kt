package out.muravev.countries.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import out.muravev.countries.R
import out.muravev.countries.fragments.CountryInfoFragment
import out.muravev.countries.fragments.CountryListFragmentImpl
import out.muravev.countries.tools.DeviceChecker

class MainActivity : AppCompatActivity() {

    private val countrySearchFragment = CountryListFragmentImpl()
    private val countryInfoFragment = CountryInfoFragment()
    private val deviceChecker = DeviceChecker(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            if (deviceChecker.isDeviceTablet()) {
                initializeMainScreenOnTablet()
            } else {
                initializeMainScreenOnPhone()
            }
        }
    }

    private fun initializeMainScreenOnTablet() {
        replaceFragment(R.id.country_list_container, countrySearchFragment)
        replaceFragment(R.id.country_info_container, countryInfoFragment)
    }

    private fun initializeMainScreenOnPhone() {
        replaceFragment(R.id.country_container, countrySearchFragment)
    }

    private fun replaceFragment(containerId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}
