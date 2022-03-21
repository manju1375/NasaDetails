package com.manju1375.nasadetails.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.manju1375.nasadetails.R
import com.manju1375.nasadetails.databinding.LayoutGalleryBinding
import com.manju1375.nasadetails.ui.adapter.NasaImageListAdapter
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * [NasaGalleryFragment] Nasa pics gallery fragment
 */
@AndroidEntryPoint
class NasaGalleryFragment : Fragment(),NasaImageListAdapter.onNasaItemClickListener {

    private var _binding: LayoutGalleryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var nasaDetailsViewModel:NasaDetailsViewModel
    @Inject lateinit var adapter:NasaImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LayoutGalleryBinding.inflate(inflater, container, false)
        binding.recyclerview.adapter = adapter
        nasaDetailsViewModel = ViewModelProvider(requireActivity()).get(NasaDetailsViewModel::class.java)
        nasaDetailsViewModel.nasaDetails.observe(viewLifecycleOwner,{ nasaDetails ->
            adapter.setDetails(nasaDetails)
        })
        nasaDetailsViewModel.progressBar.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        adapter.setOnItemClickListener(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            // load nasa pics
            nasaDetailsViewModel.readDetails()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {
        nasaDetailsViewModel.selectedItem.postValue(position)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}