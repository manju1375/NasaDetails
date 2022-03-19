package com.manju1375.nasadetails.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.manju1375.nasadetails.databinding.LayoutGalleryBinding
import com.manju1375.nasadetails.ui.recyclerview.NasaImageListAdapter
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class NasaGalleryFragment : Fragment() {

    private var _binding: LayoutGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val nasaDetailsViewModel: NasaDetailsViewModel by viewModels()
    @Inject lateinit var adapter:NasaImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LayoutGalleryBinding.inflate(inflater, container, false)
        binding.recyclerview.adapter = adapter
        nasaDetailsViewModel.nasaDetails.observe(viewLifecycleOwner,{ nasaDetails ->
            adapter.setDetails(nasaDetails)
        })
        nasaDetailsViewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            nasaDetailsViewModel.readDetails()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}