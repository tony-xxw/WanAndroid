package com.study.kotlin.wan.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
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
            disableShiftMode(bnv_navigation)
        }

    }

    override fun initView() {


    }

    override fun getLayoutId(): Int = R.layout.activity_main

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                setFragment(item.itemId)
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.navigation_home -> {

                        true
                    }
                    R.id.navigation_hot -> {

                        true
                    }
                    R.id.navigation_people -> {

                        true
                    }
                    R.id.navigation_knowledge -> {

                        true
                    }
                    else -> {
                        false
                    }
                }
            }


    private fun setFragment(index: Int) {
        fragmentManager.beginTransaction().apply {
            homeFragment ?: let {
                HomeFragment().let {
                    Log.d("XXW", "homeFragment")
                    homeFragment = it
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
            when (index) {
                R.id.navigation_home -> {
                    homeFragment?.let {
                        this.show(it)
                    }
                }
                R.id.navigation_hot -> {
                    hotFragment?.let {
                        this.show(it)
                    }
                }
                R.id.navigation_knowledge -> {
                    knowlegerFragment?.let {
                        this.show(it)
                    }
                }
                R.id.navigation_people -> {
                    peopleFragment?.let {
                        this.show(it)
                    }
                }
            }
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

    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView) {
        //由于BottomNavigationView默认第一个为选中状态，所以我们首先获取第一个条目的menuView
        val menuView = view.getChildAt(0) as BottomNavigationMenuView

        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("rcw", "无法获取mShiftingMode属性", e)
        } catch (e: IllegalAccessException) {
            Log.e("rcw", "无法修改mShiftingMode属性值", e)
        }

    }


}
