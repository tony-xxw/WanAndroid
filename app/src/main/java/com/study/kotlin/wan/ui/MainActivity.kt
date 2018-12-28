package com.study.kotlin.wan.ui


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.study.kotlin.wan.R
import com.study.kotlin.wan.base.BaseActivity
import com.study.kotlin.wan.ui.home.HomeFragment
import com.study.kotlin.wan.ui.hot.HotFragment
import com.study.kotlin.wan.ui.knowleger.KnowlegerFragment
import com.study.kotlin.wan.ui.me.PeopleFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var homeFragment: HomeFragment? = null
    private var hotFragment: HotFragment? = null
    private var knowlegerFragment: KnowlegerFragment? = null
    private var peopleFragment: PeopleFragment? = null
    private val fragmentManager by lazy {
        supportFragmentManager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnv_navigation.run {
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            selectedItemId = R.id.navigation_home
        }

    }

    override fun initView() {


    }

    override fun getLayoutId(): Int = R.layout.activity_main

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                setFragment()
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.navigation_home -> {

                        true
                    }
                    R.id.navigation_hot -> {

                        true
                    }
                    else -> {
                        false
                    }
                }
            }


    private fun setFragment() {
        fragmentManager.beginTransaction().apply {
            homeFragment ?: let {
                HotFragment().let {
                    hotFragment = it
                    add(R.id.fl_content, it)
                }
            }
            hotFragment ?: let {
                HotFragment().let {
                    hotFragment = it
                    add(R.id.fl_content, it)
                }
            }
            knowlegerFragment ?: let {
                KnowlegerFragment().let {
                    knowlegerFragment = it
                    add(R.id.fl_content, it)
                }
            }

            peopleFragment ?: let {
                PeopleFragment().let {
                    peopleFragment = it
                    add(R.id.fl_content, it)
                }
            }

            hideFragment(this)
        }.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        homeFragment?.let {
            transaction.hide(it)
        }

        hotFragment?.let {
            transaction.hide(it)
        }

        knowlegerFragment?.let {
            transaction.hide(it)
        }

        peopleFragment?.let {
            transaction.hide(it)
        }
    }
}
