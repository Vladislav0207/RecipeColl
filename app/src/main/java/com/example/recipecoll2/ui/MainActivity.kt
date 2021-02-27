package com.example.recipecoll2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import com.example.recipecoll2.R
import com.example.recipecoll2.remoteModel.RemoteModel
import com.example.recipecoll2.repository.Repository
import com.example.recipecoll2.viewModel.RecipeViewModel
import com.example.recipecoll2.viewModel.RecipeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RecipeViewModel
    lateinit var factory: RecipeViewModelFactory

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this,drawer_layout,
            R.string.open,
            R.string.close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val remoteModel = RemoteModel()
       // val localModel= LocalModel(this)
        val repository = Repository(remoteModel)
        val factory = RecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)

        viewModel.localRecipeLive.value = mutableListOf()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}