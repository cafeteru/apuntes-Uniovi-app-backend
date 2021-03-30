package es.uniovi.apuntesuniovi.security


import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.OutputStream
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class RestAccessDeniedHandler : AccessDeniedHandler {

    @Throws(IOException::class, ServletException::class)
    override fun handle(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse,
        exception: org.springframework.security.access.AccessDeniedException?
    ) {
        val response = ApiResponse(403, "Access Denied", "Access Denied")
        val out: OutputStream = httpServletResponse.outputStream
        val mapper = ObjectMapper()
        mapper.writeValue(out, response)
        out.flush()
    }
}