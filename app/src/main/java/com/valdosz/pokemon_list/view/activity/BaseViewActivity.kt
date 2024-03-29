package com.valdosz.pokemon_list.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

/**
 * Created by ByHil on 24/09/2023
 */

open class BaseViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun replaceFragment(container: Int, fragment: Fragment, bundle: Bundle?) {
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(container, fragment).commit()
    }

    fun openDialog(fragment: DialogFragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)

        fragment.show(fragmentTransaction, tag)
    }
}