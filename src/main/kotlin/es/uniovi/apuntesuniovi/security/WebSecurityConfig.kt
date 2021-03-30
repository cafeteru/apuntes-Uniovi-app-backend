package es.uniovi.apuntesuniovi.security

import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.LOGIN_URL
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.inject.Inject

/**
 * Define user permissions on endpoints
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig @Inject constructor(
    private var userDetailsService: UserDetailsServiceImpl,
    private var userService: UserService
) : WebSecurityConfigurerAdapter() {
    private val logService = LogService(this.javaClass)

    override fun configure(http: HttpSecurity) {
        logService.info("configure(httpSecurity: HttpSecurity) - start")
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers(
                LOGIN_URL, "/swagger", "/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger.json", "/"
            ).permitAll().anyRequest()
            .authenticated().and()
            .addFilter(JWTAuthenticationFilter(authenticationManager(), userService))
            .addFilter(JWTAuthorizationFilter(authenticationManager()))
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
            .authenticationEntryPoint(authenticationEntryPoint()).and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .logout().permitAll()
        logService.info("configure(httpSecurity: HttpSecurity) - end")
    }

    /**
     * Add encryptor for passwords
     */
    @Inject
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        logService.info("configureGlobal(auth: AuthenticationManagerBuilder) - start")
        auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder())
        logService.info("configureGlobal(auth: AuthenticationManagerBuilder) - start")
    }

    /**
     * Indicates the types of requests allowed
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        logService.info("corsConfigurationSource() - start")
        val source = UrlBasedCorsConfigurationSource()
        val cors = CorsConfiguration()
        cors.allowedMethods = listOf("GET", "POST", "PUT", "HEAD", "PATCH", "DELETE", "OPTIONS")
        cors.allowedHeaders = listOf("authorization", "content-type", "x-auth-token")
        source.registerCorsConfiguration("/**", cors.applyPermitDefaultValues())
        logService.info("corsConfigurationSource() - end")
        return source
    }

    @Bean
    fun accessDeniedHandler(): RestAccessDeniedHandler {
        return RestAccessDeniedHandler()
    }

    @Bean
    fun authenticationEntryPoint(): RestAuthenticationEntryPoint {
        return RestAuthenticationEntryPoint()
    }
}