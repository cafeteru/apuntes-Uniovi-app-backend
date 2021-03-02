package es.uniovi.apuntesuniovi.util

import java.util.*

class RandomMethods {
  companion object {
    private fun randomInteger(min: Int, max: Int): Int {
      var limitMax = max
      return (Random().nextFloat() * (++limitMax - min) + min).toInt()
    }

    fun randomUsername(): String {
      return "uo" + randomInteger(10_000, 99_999)
    }

    fun dni(): String {
      val numberDni = randomInteger(10_000_000, 99_999_999)
      val letterDni = "TRWAGMYFPDXBNJZSQVHLCKET"
      return "" + numberDni + letterDni[numberDni % 23]
    }

    fun nie(): String {
      val numberDni = randomInteger(1_000_000, 29_999_999)
      val letterDni = "TRWAGMYFPDXBNJZSQVHLCKET"
      if (numberDni < 10_000_000) {
        return "X" + numberDni + letterDni[numberDni % 23]
      }
      val stringDni = numberDni.toString()
      return if (stringDni[0] == '1') {
        "Y" + stringDni.substring(1) + letterDni[numberDni % 23]
      } else "Z" + stringDni.substring(1) + letterDni[numberDni % 23]
    }
  }
}