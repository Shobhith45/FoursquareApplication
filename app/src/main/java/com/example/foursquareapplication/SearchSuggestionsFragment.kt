package com.example.foursquareapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.foursquareapplication.R.layout.fragment_search_suggestions
import com.example.foursquareapplication.databinding.FragmentAboutUsBinding
import com.example.foursquareapplication.databinding.FragmentSearchSuggestionsBinding


class SearchSuggestionsFragment : Fragment() {

    private var _searchSuggestionBinding : FragmentSearchSuggestionsBinding? = null
    private val searchSuggestionBinding get() = _searchSuggestionBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _searchSuggestionBinding = FragmentSearchSuggestionsBinding.inflate(inflater,container,false)


        val names= arrayOf("Top pick","Popular","Lunch","Coffee")

        val arrayAdapter:ArrayAdapter<String> = ArrayAdapter(
            activity!!,android.R.layout.simple_list_item_1,names
        )

        searchSuggestionBinding.suggestionListView.adapter=arrayAdapter

        searchSuggestionBinding.suggestionListView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(activity,"item "+names[position],Toast.LENGTH_LONG).show()
        }


        return searchSuggestionBinding.root
    }


    }
