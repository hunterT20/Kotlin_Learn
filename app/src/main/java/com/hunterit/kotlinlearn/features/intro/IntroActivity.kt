package com.hunterit.kotlinlearn.features.intro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.hunterit.kotlinlearn.R.layout.*
import kotlinx.android.synthetic.main.intro_activity.*
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.support.v4.view.ViewPager
import android.graphics.Color
import com.hunterit.kotlinlearn.R
import com.hunterit.kotlinlearn.adapter.ViewPagerAdapter


class IntroActivity : AppCompatActivity(), ViewPager.OnPageChangeListener{
    private var layouts = intArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(R.layout.intro_activity)

        layouts = intArrayOf(
                layout_intro_1,
                layout_intro_2,
                layout_intro_3,
                layout_intro_4
        )

        val myViewPagerAdapter = ViewPagerAdapter(this, layouts)
        view_pager.adapter = myViewPagerAdapter
        view_pager.addOnPageChangeListener(this)

        addBottomDots(0)
        changeStatusBarColor()

        btn_next.setOnClickListener{
            val current = getItem(+1)
            if (current < layouts.size) {
                view_pager.currentItem = current
            } else {
                launchHomeScreen()
            }
        }

        btn_skip.setOnClickListener {
            launchHomeScreen()
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        addBottomDots(position)

        if (position == layouts.size - 1) {
            btn_next.text = getString(R.string.start)
            btn_skip.visibility = View.GONE
        } else {
            btn_next.text = getString(R.string.next)
            btn_skip.visibility = View.VISIBLE
        }
    }

    private fun addBottomDots(currentPage: Int) {
        val dots = arrayOfNulls<TextView>(layouts.size)
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        layoutDots.removeAllViews()
        for (i in 0 until dots.size) {
            dots[i] = TextView(this)
            dots[i]!!.text = '\u2022'.toString()
            dots[i]!!.textSize = 35F
            dots[i]!!.setTextColor(colorsInactive[currentPage])
            layoutDots.addView(dots[i])
        }

        if (dots.isNotEmpty())
            dots[currentPage]!!.setTextColor(colorsActive[currentPage])
    }

    private fun getItem(i: Int): Int {
        return view_pager.currentItem + i
    }

    private fun launchHomeScreen() {
        /*pref.setFirstTimeLaunch(false)
        startActivity(Intent(this@IntroActivity, IntroActivity::class.java))
        finish()*/
    }

    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}
