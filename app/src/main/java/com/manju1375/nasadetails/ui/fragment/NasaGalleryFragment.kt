package com.manju1375.nasadetails.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manju1375.nasadetails.databinding.LayoutGalleryBinding
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LayoutGalleryBinding.inflate(inflater, container, false)
        nasaDetailsViewModel.nasaDetails.observe(viewLifecycleOwner,{
            Log.d("Nasa Details:", it.toString())
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