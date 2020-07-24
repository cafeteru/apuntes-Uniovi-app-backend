package es.uniovi.apuntesuniovi.servicies.dates

import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

object DateService {

    fun dateToString(date: LocalDate?): String {
        if (date == null) {
            throw IllegalArgumentException("la fecha no puede ser nula")
        }
        return date.toString("dd-MM-yyyy")
    }

    fun dateToStringWithHour(localDateTime: LocalDateTime): String {
        return try {
            localDateTime.toString("dd-MM-yyyy HH:mm")
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("la fecha no puede ser nula")
        }
    }

    fun dateToStringOnlyHour(localTime: LocalTime): String {
        return try {
            localTime.toString("HH:mm")
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("la hora no puede ser nula")
        }
    }

    fun stringToDate(date: String): LocalDate {
        return try {
            require(date.isNotEmpty()) {
                "la fecha no puede " +
                        "estar vacía"
            }
            LocalDate.parse(date, DateTimeFormat.forPattern(
                    "dd-MM-yyyy"))
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("la fecha no puede ser nula")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("el formato de la fecha no" +
                    " es válido")
        }
    }

    fun stringToDateOnlyHour(date: String): LocalTime {
        return try {
            require(!date.isEmpty()) {
                "la fecha no puede " +
                        "estar vacía"
            }
            LocalTime.parse(date, DateTimeFormat.forPattern("HH:mm"))
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("la hora no puede ser nula")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("el formato de la hora no" +
                    " es válido")
        }
    }

    fun stringToDateWithHour(date: String): LocalDateTime {
        return try {
            require(!date.isEmpty()) {
                "la fecha no puede estar vacía"
            }
            LocalDateTime.parse(date,
                    DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"))
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("la fecha no puede ser nula")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("el formato de la fecha no" +
                    " es válido")
        }
    }

    fun stringToDateWithHour(date: String, hour: String): LocalDateTime {
        return try {
            require(date.isNotEmpty()) {
                "la fecha no puede vacía"
            }
            require(hour.isNotEmpty()) {
                "la fecha no puede estar vacía"
            }
            val newDate = "$date $hour"
            LocalDateTime.parse(newDate,
                    DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"))
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("la fecha no puede ser nula")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("el formato de la fecha no" +
                    " es válido")
        }
    }

    fun compareTwoDatesWithoutYear(
            first: LocalDate, second: LocalDate): Boolean {
        return try {
            (first.monthOfYear == second.monthOfYear
                    && first.dayOfWeek == second.dayOfWeek)
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("las dos fechas a comparar no" +
                    " deben ser nulas")
        }
    }
}