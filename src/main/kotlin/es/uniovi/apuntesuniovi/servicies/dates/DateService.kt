package es.uniovi.apuntesuniovi.servicies.dates

import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.log.LogService
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.util.*

object DateService {
    private const val dateFormat = "dd-MM-yyyy"
    private const val dateHourFormat = "dd-MM-yyyy HH:mm"
    private const val hourFormat = "HH:mm"
    private val logService = LogService(this.javaClass)

    /**
     * Converts a variable of type LocalDate to string with the format
     *
     * @param date The date to convert
     */
    fun dateToString(date: Date?): String {
        logService.info("dateToString(date: ${date}) - start")
        if (date != null) {
            val result = LocalDate(date).toString(dateFormat)
            logService.info("dateToString(date: ${date}) - end")
            return result
        }
        logService.error(ExceptionMessages.NULL_DATE)
        throw IllegalArgumentException(ExceptionMessages.NULL_DATE)
    }

    fun dateToStringWithHour(localDateTime: LocalDateTime?): String {
        logService.info("dateToStringWithHour(localDateTime: ${localDateTime}) - start")
        if (localDateTime != null) {
            val result = localDateTime.toString(dateHourFormat)
            logService.info("dateToStringWithHour(localDateTime: ${localDateTime}) - end")
            return result
        }
        logService.error(ExceptionMessages.NULL_DATE)
        throw IllegalArgumentException(ExceptionMessages.NULL_DATE)
    }

    fun dateToStringOnlyHour(localTime: LocalTime?): String {
        logService.info("dateToStringOnlyHour(localTime: ${localTime}) - start")
        if (localTime != null) {
            val result = localTime.toString(hourFormat)
            logService.info("dateToStringOnlyHour(localTime: ${localTime}) - end")
            return result
        }
        logService.error(ExceptionMessages.NULL_DATE)
        throw IllegalArgumentException(ExceptionMessages.NULL_DATE)
    }

    fun stringToDate(date: String?): LocalDate {
        logService.info("stringToDate(date: ${date}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                ExceptionMessages.NULL_EMPTY_DATE
            }
            val result = LocalDate.parse(date, DateTimeFormat.forPattern(dateFormat))
            logService.info("stringToDate(date: ${date}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            logService.error(ExceptionMessages.ILLEGAL_FORMAT_DATE)
            throw IllegalArgumentException(ExceptionMessages.ILLEGAL_FORMAT_DATE)
        }
    }

    fun stringToDateOnlyHour(date: String?): LocalTime {
        logService.info("stringToDateOnlyHour(date: ${date}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                logService.error(ExceptionMessages.NULL_EMPTY_DATE)
                ExceptionMessages.NULL_EMPTY_DATE
            }
            val result = LocalTime.parse(date, DateTimeFormat.forPattern(hourFormat))
            logService.info("stringToDateOnlyHour(date: ${date}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            logService.error(ExceptionMessages.ILLEGAL_FORMAT_HOUR)
            throw IllegalArgumentException(ExceptionMessages.ILLEGAL_FORMAT_HOUR)
        }
    }

    fun stringToDateWithHour(date: String?): LocalDateTime? {
        logService.info("stringToDateWithHour(date: ${date}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                logService.error(ExceptionMessages.NULL_EMPTY_DATE)
                ExceptionMessages.NULL_EMPTY_DATE
            }
            val result = LocalDateTime.parse(date, DateTimeFormat.forPattern(dateHourFormat))
            logService.info("stringToDateWithHour(date: ${date}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            logService.error(ExceptionMessages.ILLEGAL_FORMAT_DATE)
            throw IllegalArgumentException(ExceptionMessages.ILLEGAL_FORMAT_DATE)
        }
    }

    fun stringToDateWithHour(date: String?, hour: String?): LocalDateTime {
        logService.info("stringToDateWithHour(date: ${date}, hour: ${hour}) - start")
        try {
            require(!date.isNullOrEmpty()) {
                logService.error(ExceptionMessages.NULL_EMPTY_DATE)
                ExceptionMessages.NULL_EMPTY_DATE
            }
            require(!hour.isNullOrEmpty()) {
                logService.error(ExceptionMessages.NULL_EMPTY_HOUR)
                ExceptionMessages.NULL_EMPTY_HOUR
            }
            val newDate = "$date $hour"
            val result = LocalDateTime.parse(newDate, DateTimeFormat.forPattern(dateHourFormat))
            logService.info("stringToDateWithHour(date: ${date}, hour: ${hour}) - end")
            return result
        } catch (e: IllegalArgumentException) {
            logService.error(ExceptionMessages.ILLEGAL_FORMAT_DATE)
            throw IllegalArgumentException(ExceptionMessages.ILLEGAL_FORMAT_DATE)
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
            logService.error(ExceptionMessages.NULL_EMPTY_DATE)
            ExceptionMessages.NULL_EMPTY_DATE
        }
        require(second == null) {
            logService.error(ExceptionMessages.NULL_EMPTY_DATE)
            ExceptionMessages.NULL_EMPTY_DATE
        }
        return result
    }
}