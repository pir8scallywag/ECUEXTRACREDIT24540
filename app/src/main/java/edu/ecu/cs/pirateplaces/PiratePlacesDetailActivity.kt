package edu.ecu.cs.pirateplaces

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

const val EXTRA_PLACE_ID = "edu.ecu.cs.pirateplaces.place_id"

class PiratePlacesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pirate_places_list)

        val placeId = intent.getSerializableExtra(EXTRA_PLACE_ID) as? UUID



        val  position =  intent.getSerializableExtra("position") as? Int

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = PiratePlacesDetailFragment.newInstance(placeId!!,  position!!)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    companion object {
        fun newIntent(packageContext: Context, id: UUID,  position: Int): Intent {
            return Intent(packageContext, PiratePlacesDetailActivity::class.java).apply {
                putExtra(EXTRA_PLACE_ID, id)
                putExtra("position",  position)
            }
        }
    }
}
