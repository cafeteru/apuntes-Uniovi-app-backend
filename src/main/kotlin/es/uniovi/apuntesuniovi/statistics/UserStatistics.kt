package es.uniovi.apuntesuniovi.statistics

data class UserStatistics(
    var active: Int,
    var inactive: Int,
    var numStudents: Int,
    var numTeachers: Int,
    var numAdmin: Int
)