package es.uniovi.apuntesuniovi.servicies.dates

import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.log.LogService
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

object DateService {
    private const val dateFormat = "dd-MM-yyyy"
    private const val dateHourFormat = "dd-MM-yyyy HH:mm"
    private const val hourFormat = "HH:mm"
    private val logService = LogService(this.javaClass)

    /**
     * Convierte una variable de tipo LocalDate a string con el formato
     *
     * @param date La fecha a convertir.
     */
    fun dateToString(date: LocalDate?): String {
        logService.info("dateToString(date: ${date}) - start")
        if (date != null) {
            val result = date.toString(dateFormat)
            logService.info("dateToString(date: ${date}) - end")
            return result
        }
        throw IllegalArgumentException(ExceptionMessages.nullDate)
    }

    fun dateToStringWithHour(localDateTime: LocalDateTime?): String {
        logService.info("dateToStringWithHour(localDateTime: ${localDateTime}) - start")
        if (localDateTime != null) {
            val result = localDateTime.toString(dateHourFormat)
            logService.info("dateToStringWithHour(localDateTime: ${localDateTime}) - end")
            return result
        }
        throw IllegalArgumentException(ExceptionMessages.nullDate)
    }

    fun dateToStringOnlyHour(localTime: LocalTime?): String {
        logService.info("dateToStringOnlyHour(localTime: ${localTime}) - start")
        if (localTime != null) {
            val result = localTime.toString(hourFormat)
            logService.info("dateToStringOnlyHour(localTime: ${localTime}) - end")
            return result
        }
        throw IllegalArgumentException(ExceptionMessages.nullDate)
    }

    fun stringToDate(date: String?): LocalDate {
        logService.info("stringToDate(date: ${date}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                ExceptionMessages.nullEmptyDate
            }
            val result = LocalDate.parse(date, DateTimeFormat.forPattern(dateFormat))
            logService.info("stringToDate(date: ${date}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(ExceptionMessages.illegalFormatDate)
        }
    }

    fun stringToDateOnlyHour(date: String?): LocalTime {
        logService.info("stringToDateOnlyHour(date: ${date}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                ExceptionMessages.nullEmptyDate
            }
            val result = LocalTime.parse(date, DateTimeFormat.forPattern(hourFormat))
            logService.info("stringToDateOnlyHour(date: ${date}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(ExceptionMessages.illegalFormatHour)
        }
    }

    fun stringToDateWithHour(date: String?): LocalDateTime? {
        logService.info("stringToDateWithHour(date: ${date}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                ExceptionMessages.nullEmptyDate
            }
            val result = LocalDateTime.parse(date, DateTimeFormat.forPattern(dateHourFormat))
            logService.info("stringToDateWithHour(date: ${date}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(ExceptionMessages.illegalFormatDate)
        }
    }

    fun stringToDateWithHour(date: String?, hour: String?): LocalDateTime {
        logService.info("stringToDateWithHour(date: ${date}, hour: ${hour}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                ExceptionMessages.nullEmptyDate
            }
            require(!hour.isNullOrEmpty()) {
                ExceptionMessages.nullEmptyHour
            }
            val newDate = "$date $hour"
            val result = LocalDateTime.parse(newDate, DateTimeFormat.forPattern(dateHourFormat))
            logService.info("stringToDateWithHour(date: ${date}, hour: ${hour}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(ExceptionMessages.illegalFormatDate)
        }
    }

    fun compareTwoDatesWithoutYear(first: LocalDate?, second: LocalDate?): Boolean {
        logService.info("compareTwoDatesWithoutYear(first: ${first}, second: ${second}) - start")
        var result = false
        if (first != null && second != null) {
            result = (first.monthOfYear == second.monthOfYear && first.dayOfWeek == second.dayOfWeek)
            logService.info("compareTwoDatesWithoutYear(first: ${first}, second: ${second}) - end")
        }
        require(first == null) {
            ExceptionMessages.nullEmptyDate
        }
        require(second == null) {
            ExceptionMessages.nullEmptyDate
        }
        return result
    }
}