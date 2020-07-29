package es.uniovi.apuntesuniovi.servicies.security

import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.LOGIN_URL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.inject.Inject


@Configuration
@EnableWebSecurity
class WebSecurity @Inject constructor(
        private var userDetailsService: UserDetailsServiceImpl
) : WebSecurityConfigurerAdapter() {
    private val logService = LogService(this.javaClass)

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        logService.info("configure(httpSecurity: HttpSecurity) - start")
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(LOGIN_URL).permitAll().anyRequest()
                .authenticated().and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .logout().permitAll()
        logService.info("configure(httpSecurity: HttpSecurity) - end")
    }

    @Inject
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        logService.info("configureGlobal(auth: AuthenticationManagerBuilder) - start")
        auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder())
        logService.info("configureGlobal(auth: AuthenticationManagerBuilder) - start")
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        logService.info("corsConfigurationSource() - start")
        val source = UrlBasedCorsConfigurationSource()
        val cors = CorsConfiguration()
        cors.allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        cors.allowedHeaders = listOf("authorization", "content-type", "x-auth-token")
        source.registerCorsConfiguration("/**", cors.applyPermitDefaultValues())
        logService.info("corsConfigurationSource() - end")
        return source
    }

}