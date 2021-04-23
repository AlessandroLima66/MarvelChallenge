package br.com.alessandro.marvelchallenge.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import br.com.alessandro.marvelchallenge.R
import br.com.alessandro.marvelchallenge.feature.personage.ui.PersonageListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(PersonageListFragment.newInstance())
    }

    private fun startFragment(fragment: Fragment) {
        transaction {
            replace(R.id.frame, fragment)
            this.addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    inline fun AppCompatActivity.transaction(func: FragmentTransaction.() -> Unit) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.run(func)
        transaction.commit()
    }
}
