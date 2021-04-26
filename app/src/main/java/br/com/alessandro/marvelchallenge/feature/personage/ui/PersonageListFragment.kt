package br.com.alessandro.marvelchallenge.feature.personage.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alessandro.marvelchallenge.R
import br.com.alessandro.marvelchallenge.data.model.Character
import br.com.alessandro.marvelchallenge.feature.personage.adapter.PersonagesAdapter
import br.com.alessandro.marvelchallenge.feature.personage.adapter.PersonagesCarouselAdapter
import br.com.alessandro.marvelchallenge.feature.personage.viewmodel.PersonageViewModel
import br.com.alessandro.marvelchallenge.feature.personage.viewstate.PersonageViewState
import br.com.alessandro.marvelchallenge.util.toAccessibilityHeaderType
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PersonageListFragment : Fragment() {

    private lateinit var titleCarousel:TextView
    private lateinit var carousel: RecyclerView
    private lateinit var titleList:TextView
    private lateinit var list: RecyclerView
    private lateinit var carouselShimmer: ShimmerFrameLayout
    private lateinit var listShimmer: ShimmerFrameLayout
    private lateinit var personagesAdapter: PersonagesAdapter
    private lateinit var personagesCarouselAdapter: PersonagesCarouselAdapter
    private val viewModel: PersonageViewModel by viewModel()
    private val picasso: Picasso by inject()

    private val dialogError: AlertDialog by lazy {
        AlertDialog.Builder(context)
            .setPositiveButton(R.string.button_ok) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(
        R.layout.personage_fragment,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupAcessibility()
        observers()
        viewModel.getPersonages()
    }

    private fun initViews(view: View) {
        titleCarousel = view.findViewById(R.id.personage_title_carousel)
        carousel = view.findViewById(R.id.personage_carousel)
        titleList = view.findViewById(R.id.personage_title_list)
        list = view.findViewById(R.id.personage_list)
    }

    private fun setupAcessibility() {
        titleList.toAccessibilityHeaderType()
        titleCarousel.toAccessibilityHeaderType()
    }

    private fun setupList(listPersonages: MutableList<Character>) {
        personagesAdapter =
            PersonagesAdapter(
                listPersonages,
                picasso
            )

        list.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = personagesAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1) &&
                        newState == RecyclerView.SCROLL_STATE_IDLE) {

                        Toast.makeText(
                            context,
                            getString(R.string.message_loading),
                            Toast.LENGTH_SHORT
                        ).show()

                        viewModel.getMorePersonages()
                    }
                }
            })
        }
    }

    private fun setupCarousel(listPersonages: MutableList<Character>) {
        personagesCarouselAdapter =
            PersonagesCarouselAdapter(
                listPersonages,
                picasso
            )

        carousel.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = personagesCarouselAdapter
        }
    }

    private fun observers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {

                is PersonageViewState.ShowLists -> {
                    setupCarousel(state.listCarousel)
                    setupList(state.list)
                }

                is PersonageViewState.UpdateList -> {
                    personagesAdapter.updateItems(state.list)
                }

                is PersonageViewState.Error -> {
                    dialogError.apply {
                        setMessage(getString(state.message))
                        show()
                    }
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = PersonageListFragment()
    }
}
