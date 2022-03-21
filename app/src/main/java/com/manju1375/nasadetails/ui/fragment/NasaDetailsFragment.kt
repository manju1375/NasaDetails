package com.manju1375.nasadetails.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.manju1375.nasadetails.databinding.LayoutDetailsBinding
import com.manju1375.nasadetails.model.NasaItemResponse
import com.manju1375.nasadetails.ui.adapter.NasaDetailsPagerAdapter
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.nasa_details_pager_item.*
import javax.inject.Inject


/**
 * [NasaDetailsFragment] Details Fragment
 */
@AndroidEntryPoint
class NasaDetailsFragment : Fragment() {

    private var _binding: LayoutDetailsBinding? = null

    val nasaDetailsViewModel:NasaDetailsViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    @Inject lateinit var nasaDetailsPagerAdapter:NasaDetailsPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutDetailsBinding.inflate(inflater, container, false)
        //nasaDetailsViewModel = ViewModelProvider(requireActivity()).get(NasaDetailsViewModel::class.java)
        nasaDetailsViewModel.nasaDetails.value?.let { nasaDetailsPagerAdapter.setNasaDetails(it) }
        val viewPager = binding.viewpager
        viewPager.adapter = nasaDetailsPagerAdapter
        nasaDetailsViewModel.selectedItem.value?.let { viewPager.setCurrentItem(it,true) }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}