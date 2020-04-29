package es.uniovi.apuntesuniovi.servicies.security

import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.inject.Inject

@Configuration
@EnableWebSecurity
class WebSecurity @Autowired constructor(
        private val userDetailsService: UserDetailsServiceImpl,
        private val serviceFactory: ServiceFactory
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val source = UrlBasedCorsConfigurationSource()
        val cors = CorsConfiguration()
        cors.allowedMethods = listOf("GET", "POST", "PUT", "PATCH",
                "DELETE", "OPTIONS")
        cors.allowedHeaders = listOf("authorization", "content-type",
                "x-auth-token")
        source.registerCorsConfiguration("/**",
                cors.applyPermitDefaultValues())
        return source
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/v2/api-docs", "/swagger-resources/**",
                        "/swagger-ui.html", "/webjars/**", "/swagger.json", "/")
                .permitAll().antMatchers("/login").permitAll().anyRequest()
                .authenticated().and()
                .addFilter(JWTAuthenticationFilter(authenticationManager(),
                        serviceFactory))
                .addFilter(JWTAuthorizationFilter(authenticationManager())) // this disables session creation on Spring Security
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .logout().permitAll()
    }

    @Inject
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService?>(userDetailsService)
                .passwordEncoder(BCryptPasswordEncoder())
    }
}