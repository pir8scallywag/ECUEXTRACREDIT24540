package edu.ecu.cs.pirateplaces

import java.util.*

public class PirateBase {
    companion object {
        private val sampleNames = listOf(
            "Bill", "James", "Edward", "Mary", "Alice", "Susan", "Joe", "Beth"
        )

        val piratePlaceList = List(100) {
            PiratePlace(name="Place $it", visitedWith= sampleNames[it % sampleNames.size])
        }

        fun getPlaceById(id: UUID): PiratePlace  {
            val place = piratePlaceList.findLast { place -> place.id == id }
            if (place == null) {
                throw IllegalArgumentException("Bad id ${id}")
            } else {
                return place
            }
        }
    }
}