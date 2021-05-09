package com.example.foursquareapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foursquareapplication.R
import com.example.foursquareapplication.adapter.ReviewAdapter
import com.example.foursquareapplication.databinding.ActivityReviewBinding
import com.example.foursquareapplication.helper.Constants
import com.example.foursquareapplication.viewmodel.ReviewViewModel

class ReviewActivity : AppCompatActivity() {

    private lateinit var reviewBinding: ActivityReviewBinding
    private lateinit var reviewAdapter : ReviewAdapter
    private lateinit var reviewViewModel : ReviewViewModel
    private var isLoggedIn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reviewBinding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(reviewBinding.root)

        setToolbar()
        loadReviewData()


    }

    override fun onResume() {
        super.onResume()
        loadReviewData()
    }

    private fun loadReviewData() {
        val sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCE, MODE_PRIVATE)
        isLoggedIn = sharedPreferences.contains(Constants.USER_ID)
        reviewViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ReviewViewModel::class.java)
        reviewAdapter = ReviewAdapter(this)
        reviewBinding.reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        reviewBinding.reviewRecyclerView.adapter = reviewAdapter


        val placeId = intent.getIntExtra(Constants.PLACE_ID,0)
        if(placeId!=0) {
            reviewViewModel.getReview(placeId)?.observe(this, {
                if (it != null) {

                    reviewAdapter.submitList(it)

                }


            })
        }
    }

    private fun setToolbar() {
        reviewBinding.toolbar.setNavigationIcon(R.drawable.back_icon)
        reviewBinding.toolbar.inflateMenu(R.menu.menu_review)
        reviewBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        reviewBinding.toolbarTitle.text = intent.getStringExtra(Constants.PLACE_NAME)
        reviewBinding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add_review -> {
                    val addReviewIntent = Intent(this,AddReviewActivity::class.java)
                    val placeId = intent.extras?.getInt(Constants.PLACE_ID)
                    val placeName = intent.extras?.getString(Constants.PLACE_NAME)
                    addReviewIntent.putExtra(Constants.PLACE_ID, placeId)
                    addReviewIntent.putExtra(Constants.PLACE_NAME,placeName)
                    startActivity(addReviewIntent)
                }
            }
            true

        }
    }

}