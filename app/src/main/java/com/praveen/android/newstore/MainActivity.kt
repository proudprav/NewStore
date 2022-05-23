package com.praveen.android.newstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.praveen.android.newstore.ui.view.ProductListFragement
import com.praveen.android.newstore.utils.FragmentNavigationInterface


class MainActivity : AppCompatActivity(), FragmentNavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            replaceFragment(ProductListFragement(this))

        }
    }

    override fun replaceFragment(fragment: Fragment) {
        val fManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fManager.beginTransaction()
        if (fManager.backStackEntryCount == 0) {
            transaction.replace(R.id.container, fragment, fragment.tag)
            transaction.addToBackStack("1st")
        } else {
            transaction.replace(R.id.container, fragment)
        }
        transaction.commit()
    }

}