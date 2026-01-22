package com.tanviraut.sampleapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanviraut.sampleapp.data.api.ApiHelper
import com.tanviraut.sampleapp.data.api.RetrofitBuilder
import com.tanviraut.sampleapp.data.model.User
import com.tanviraut.sampleapp.databinding.ActivityMainBinding
import com.tanviraut.sampleapp.ui.base.ViewModelFactory
import com.tanviraut.sampleapp.ui.main.adapter.MainAdapter
import com.tanviraut.sampleapp.ui.main.viewmodel.MainViewModel
import com.tanviraut.sampleapp.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory(
            ApiHelper(RetrofitBuilder.apiService)
        )
        viewModel = ViewModelProvider(this, factory)
            .get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MainAdapter(arrayListOf())

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        binding.recyclerView.adapter = adapter
    }

    private fun setObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }

                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }

                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>){
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }


}
