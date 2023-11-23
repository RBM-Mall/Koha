package com.bbb.koha.new_dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.bbb.koha.R
import com.bbb.koha.databinding.ActivityDashboardBinding


class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onViewClick()
        viewHideGone()

        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.flFragment, fragment)
            .commit()

    }
    private fun viewHideGone(){
        binding.lnrHome.setOnClickListener {
            if (binding.consMyAccount.visibility == View.VISIBLE) {
                binding.consMyAccount.visibility = View.GONE
                binding.lnrHome.setBackgroundResource(R.color.white)
            } else {
                binding.consMyAccount.visibility = View.VISIBLE
                binding.lnrHome.setBackgroundResource(R.color.btbBack)
            }
        }


        binding.lnrLibrary.setOnClickListener {
            if (binding.consAboutLibrary.visibility == View.VISIBLE) {
                binding.consAboutLibrary.visibility = View.GONE
                binding.lnrLibrary.setBackgroundResource(R.color.white)
            } else {
                binding.consAboutLibrary.visibility = View.VISIBLE
                binding.lnrLibrary.setBackgroundResource(R.color.btbBack)
            }
        }

    }

    private fun onViewClick() {

        binding.infoTools.toolbarDrawer.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                closeDrawer()
            } else {
                openDrawer()
            }
        }
        binding.ivClose.setOnClickListener {
            closeDrawer()
        }
        binding.consHome.setOnClickListener {
            binding.consHome.setBackgroundResource(R.color.btbBack)
            binding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.ivHomee.setImageResource(R.drawable.baseline_home_24)

        }
        binding.consNotification.setOnClickListener {
            binding.consNotification.setBackgroundResource(R.color.btbBack)
            binding.tvNotification.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.ivNotification.setImageResource(R.drawable.notification_white)

        }

        binding.consSearchCircle.setOnClickListener {
            binding.consSearchCircle.setBackgroundResource(R.color.btbBack)
            binding.tvSearchCircle.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.ivNotification.setImageResource(R.drawable.notification_white)

        }

        binding.consFolderSearch.setOnClickListener {
            binding.consFolderSearch.setBackgroundResource(R.color.btbBack)
            binding.tvSearchBook.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.ivNotification.setImageResource(R.drawable.notification_white)

        }

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }
}