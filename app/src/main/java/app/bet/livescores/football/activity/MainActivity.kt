package app.bet.livescores.football.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import app.bet.livescores.football.ConstVal
import app.bet.livescores.football.R

import app.bet.livescores.football.activity.presenter.MainActivityPresenter
import app.bet.livescores.football.activity.view.MainActivityView
import app.bet.livescores.football.data.model.IpData
import app.bet.livescores.football.dependencies
import app.bet.livescores.football.features.basketball.BasketballFragment
import app.bet.livescores.football.features.hockey.HockeyFragment
import app.bet.livescores.football.features.soccer.SoccerFragment
import app.bet.livescores.football.features.soccergermany.SoccerGermanyFragment
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.io.InputStream
import java.security.MessageDigest
import java.util.*

class MainActivity : MvpAppCompatActivity(), MainActivityView {
    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter {
        val (repository, schedulers) = baseContext.dependencies()
        return MainActivityPresenter(repository, schedulers)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(SoccerFragment.newInstance(), SoccerFragment.TAG)
        bottom_navigation.selectedItemId = R.id.menu_item_soccer
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_basketball -> {
                    replaceFragment(BasketballFragment.newInstance(), BasketballFragment.TAG)
                    true
                }
                R.id.menu_item_soccer -> {
                    replaceFragment(SoccerFragment.newInstance(), SoccerFragment.TAG)
                    true
                }
                R.id.menu_item_soccer_germany -> {
                    replaceFragment(SoccerGermanyFragment.newInstance(), SoccerGermanyFragment.TAG)
                    true
                }
                R.id.menu_item_hockey -> {
                    replaceFragment(HockeyFragment.newInstance(), HockeyFragment.TAG)
                    true
                }

                else -> false
            }

        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val currentFragment = getCurrentFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        if (currentFragment != null && currentFragment.tag == tag) {
            transaction.replace(R.id.main_container, currentFragment, tag)
                .commit()
            return
        }
        val resultFragment = fragmentManager.findFragmentByTag(tag)

        if (resultFragment == null) {
            transaction.replace(R.id.main_container, fragment, tag)
        } else {
            transaction.replace(R.id.main_container, resultFragment, tag)
        }
        transaction.commit()
    }

    private fun getCurrentFragment(): Fragment? {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) {
                return fragment
            }
        }
        return null
    }

    override fun getWrapData(data: String) {
        equalsLinks(data)
    }

    override fun getIpData(ip: IpData) {
        if (getCheckIP(baseContext, ip.ip))presenter.getDataWrapper()

    }

    private fun equalsLinks(links: String) {
        if (links.isNotEmpty() && links.startsWith("http")) {
            val webActivity = Intent(this, WebActivity::class.java).apply{
               putExtra(ConstVal.REGION, links)
            }
            startActivity(webActivity)
        }
    }

    private fun getCheckIP(context: Context, ip: String): Boolean {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.ips)
        val scanner = Scanner(inputStream)
        while (scanner.hasNext()) {
            val wrongIp = scanner.nextLine()
            if (ip == wrongIp) return false
        }
        return true

    }


    }



