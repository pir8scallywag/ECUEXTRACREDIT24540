package edu.ecu.cs.pirateplaces

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import java.util.*

private const val ARG_PLACE_ID = "place_id"
private const val DIALOG_TIME = "DialogTime"
private const val REQUEST_TIME = 1

class PiratePlacesDetailFragment: Fragment(), TimePickerFragment.Callbacks {

    private lateinit var place: PiratePlace
    private lateinit var placeNameField : EditText

    private lateinit var guestsField: EditText
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button
    private lateinit var compare_names: Button

    private var TAG =  "PriatePlacesDetailsFragment"

    var position = 0

    private val piratePlacesDetailViewModel : PiratePlacesDetailViewModel by lazy {
        ViewModelProviders.of(this).get(PiratePlacesDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getSerializable(ARG_PLACE_ID) as UUID
        position = arguments?.getInt("position") as Int
        place = PirateBase.getPlaceById(id)
        piratePlacesDetailViewModel.place = place
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pirate_places_detail, container, false)


        placeNameField = view.findViewById(R.id.place_name) as EditText
        guestsField = view.findViewById(R.id.visited_with) as EditText
        dateButton = view.findViewById(R.id.check_in_date) as Button
        timeButton = view.findViewById(R.id.check_in_time) as Button
        compare_names = view.findViewById(R.id.compare_names) as Button

        dateButton.isEnabled = false

        timeButton.setOnClickListener {
            TimePickerFragment.newInstance(place.lastVisited).apply {
                setTargetFragment(this@PiratePlacesDetailFragment, REQUEST_TIME)
                show(this@PiratePlacesDetailFragment.parentFragmentManager, DIALOG_TIME)
            }
        }



        updateUI()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val placeNameWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                place.name = p0.toString()
            }
        }

        val visitedWithWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                place.visitedWith = p0.toString()
            }
        }

        placeNameField.addTextChangedListener(placeNameWatcher)
        guestsField.addTextChangedListener(visitedWithWatcher)
    }

    override fun onTimeSelected(date: Date) {
        place.lastVisited = date
        updateUI()
    }

    fun updateUI() {

        val visitedDate = DateFormat.getMediumDateFormat(context).format(place.lastVisited)
        val visitedTime = DateFormat.getTimeFormat(context).format(place.lastVisited)
        compare_names.setText(place.name)
        placeNameField.setText(place.name)
        guestsField.setText(place.visitedWith)
        dateButton.setText(visitedDate)
        timeButton.setText(visitedTime)
    }

    companion object {
        fun newInstance(id: UUID, position: Int) : PiratePlacesDetailFragment {
            val args = Bundle().apply {
                putSerializable(ARG_PLACE_ID, id)
                putInt("position", position)
            }
            return PiratePlacesDetailFragment().apply {
                arguments = args
            }
        }
    }

}