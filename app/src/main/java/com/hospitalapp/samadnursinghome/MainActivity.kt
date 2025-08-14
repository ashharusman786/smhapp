package com.hospitalapp.samadnursinghome

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter // Or FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager // Ensure this is the correct ViewPager import
import com.google.android.material.navigation.NavigationView
import com.hospitalapp.samadnursinghome.databinding.ActivityMainBinding
import com.hospitalapp.samadnursinghome.fragments.AboutUsFragment // Import your fragments
import com.hospitalapp.samadnursinghome.fragments.AppointmentFragment
import com.hospitalapp.samadnursinghome.fragments.ContactUsFragment
import com.hospitalapp.samadnursinghome.fragments.DepartmentFragment
import com.hospitalapp.samadnursinghome.fragments.DoctorsFragment
import com.hospitalapp.samadnursinghome.fragments.HomeFragment
import com.hospitalapp.samadnursinghome.fragments.OurServicesFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Assuming your toolbar is within an included layout referenced by appBarMain
        // e.g., <include android:id="@+id/app_bar_main" layout="@layout/app_bar_main" />
        // And app_bar_main.xml contains the Toolbar with id 'toolbar'
        setSupportActionBar(binding.appBarMain.toolbar) // Correctly reference toolbar via binding

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar, // Pass the toolbar here for the hamburger icon
            R.string.OpenDrawer, // Ensure these strings are in strings.xml
            R.string.CloseDrawer
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState() // Syncs the toggle state with the drawer (displays hamburger icon)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Show hamburger icon
        supportActionBar?.setHomeButtonEnabled(true)


        binding.navView.setNavigationItemSelectedListener(this)

        setupViewPager()
    }

    private fun setupViewPager() {
        pagerAdapter = ViewPagerAdapter(supportFragmentManager)

        // Add your fragments to the ViewPager
        // Make sure the titles used here exactly match what you might compare against
        // in onNavigationItemSelected if you were to use titles for navigation (though index is better).
        pagerAdapter.addFragment(HomeFragment(), "Home")
        pagerAdapter.addFragment(AboutUsFragment(), "About Us")
        pagerAdapter.addFragment(DepartmentFragment(), "Departments")
        pagerAdapter.addFragment(DoctorsFragment(), "Doctors")
        pagerAdapter.addFragment(OurServicesFragment(), "Our Services")
        pagerAdapter.addFragment(AppointmentFragment(), "Appointment")
        pagerAdapter.addFragment(ContactUsFragment(), "Contact Us")

        // Assuming your ViewPager is within an included layout referenced by appBarMain
        binding.appBarMain.viewpagerId.adapter = pagerAdapter // Correctly reference ViewPager
        // Assuming your TabLayout is within an included layout referenced by appBarMain
        binding.appBarMain.tablayoutId.setupWithViewPager(binding.appBarMain.viewpagerId) // Correctly reference TabLayout
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Pass the event to ActionBarDrawerToggle, if it returns true, then it has handled the app icon touch event
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var targetFragmentPosition = -1

        when (item.itemId) {
            R.id.nav_home -> targetFragmentPosition = pagerAdapter.getPositionByTitle("Home")
            R.id.nav_about_us -> targetFragmentPosition = pagerAdapter.getPositionByTitle("About Us")
            R.id.nav_departments -> targetFragmentPosition = pagerAdapter.getPositionByTitle("Departments")
            R.id.nav_doctors -> targetFragmentPosition = pagerAdapter.getPositionByTitle("Doctors")
            R.id.nav_services -> targetFragmentPosition = pagerAdapter.getPositionByTitle("Our Services")
            R.id.nav_appointment -> targetFragmentPosition = pagerAdapter.getPositionByTitle("Appointment")
            R.id.nav_contact -> targetFragmentPosition = pagerAdapter.getPositionByTitle("Contact Us")

            R.id.nav_rate_us -> {
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                } catch (e: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                }
            }
            R.id.nav_share_app -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val shareBody = "Check out Samad Nursing Home App: https://play.google.com/store/apps/details?id=$packageName"
                val shareSub = "Samad Nursing Home App"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(shareIntent, "Share using"))
            }
            R.id.nav_privacy_policy -> {
                // Example: Open a privacy policy URL
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("YOUR_PRIVACY_POLICY_URL_HERE"))
                startActivity(browserIntent)
            }
            else -> Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show() // Default for unhandled items
        }

        if (targetFragmentPosition != -1) {
            binding.appBarMain.viewpagerId.currentItem = targetFragmentPosition
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // --- ViewPagerAdapter Inner Class ---
    // Make sure this adapter is suitable for your ViewPager (ViewPager or ViewPager2)
    // This is for the older ViewPager. For ViewPager2, you'd use FragmentStateAdapter.
    class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }

        fun getPositionByTitle(title: String): Int {
            return fragmentTitleList.indexOfFirst { it.equals(title, ignoreCase = true) }
        }
    }
}
