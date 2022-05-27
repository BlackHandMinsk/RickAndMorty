package com.example.rickandmorty.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersInfoBinding
import com.example.rickandmorty.domain.model.CharacterInfo
import com.example.rickandmorty.domain.util.Resource
import com.example.rickandmorty.presentation.ui.viewmodels.viewmodels.CharactersInfoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CharactersInfoFragment : Fragment() {
    private var _binding: FragmentCharactersInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersInfoViewModel by viewModels()
    private val character by lazy { CharactersInfoFragmentArgs.fromBundle(requireArguments()).characterInfo }

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacter(character.id.toString())
        binding.toolbar.apply {
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.characterInfo.collect {
                when (it) {
                    is Resource.Success -> {
                        setInfo(it.data)
                    }
                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG)
                            .show()
                    }
                    else -> {}
                }
            }
        }
    }



    private fun setInfo(characterInfo: CharacterInfo) {
        with(binding) {
            glide.load(characterInfo.image).into(ivInfoFragmentAvatar)
            toolbarTitle.text = characterInfo.name
            tvValueName.text = characterInfo.name
            tvValueSpecies.text = characterInfo.species
            tvValueGender.text = characterInfo.gender
            tvValueStatus.text = characterInfo.status
            tvValueLocation.text = characterInfo.location
            tvValueEpisode.text = characterInfo.episode.size.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}