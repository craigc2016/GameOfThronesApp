package com.xdesign.takehome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import com.xdesign.takehome.MainActivity
import com.xdesign.takehome.R
import com.xdesign.takehome.common.Dialog
import com.xdesign.takehome.common.Resource
import com.xdesign.takehome.databinding.ActivityMainBinding
import com.xdesign.takehome.databinding.FragmentHomeBinding
import com.xdesign.takehome.models.ApiCharacter
import com.xdesign.takehome.ui.CharacterRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()
    private var characterAdapter : CharacterRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharactersList()
        handleSearchView()
    }

    private fun initCharactersList() {
        homeViewModel.getCharacters()
        homeViewModel.charactersLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        if (characterAdapter == null) {
                            characterAdapter = CharacterRecyclerViewAdapter(response.data)
                        } else {
                            characterAdapter?.updateList(response.data)
                        }
                    }
                    initRecyclerView()
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { errorMsg ->
                        context?.let {
                            Dialog.show(
                                context = it,
                                title = it.getString(R.string.error_title),
                                errorMsg
                            ) { dialog ->
                                dialog.dismiss()
                            }
                        }

                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun handleSearchView() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrEmpty()) {
                        initCharactersList()
                        return false
                    }
                    homeViewModel.searchCharacter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty()) {
                        initCharactersList()
                    }
                    return false
                }
            })

        binding.searchView.setOnCloseListener {
            initCharactersList()
            false
        }
    }

    private fun initRecyclerView(){
        binding.charactersRV.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        binding.homeProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.homeProgressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}