package es.uniovi.apuntesuniovi.security

class ApiResponse(
    var status: Int = 0,
    var message: String = "",
    var result: Any? = null
) {

}