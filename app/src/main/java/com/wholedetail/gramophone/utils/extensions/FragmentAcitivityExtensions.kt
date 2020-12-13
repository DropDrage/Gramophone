package com.wholedetail.gramophone.utils.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addBackStackFragment(@IdRes containerId: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().addToBackStack(null).add(containerId, fragment).commit()

fun FragmentActivity.replaceFragment(@IdRes id: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().replace(id, fragment).commit()

fun FragmentActivity.replaceFragmentAddBackStack(@IdRes id: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().addToBackStack(null).replace(
        id,
        fragment,
        fragment::class.java.simpleName
    ).commit()