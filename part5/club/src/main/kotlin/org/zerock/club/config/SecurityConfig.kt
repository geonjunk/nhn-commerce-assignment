package org.zerock.club.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.zerock.club.security.ApiCheckFilter
import org.zerock.club.security.handler.ClubLoginSuccessHandler
import org.zerock.club.security.service.ClubUserDetailsService


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(val userDetailsService: ClubUserDetailsService) {

    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }

//    @Bean
//    fun userDetailsService(): InMemoryUserDetailsManager {
//        val user: UserDetails = User
//            .withUsername("user1")
//            .password("$2a$10$9K5PHHYvywPEO9aKj0k1Ke7z.gEOr5jT6i5T/95ck6.rhhV2e8MB2")
//            .roles("USER")
//            .build()
//        return InMemoryUserDetailsManager(user)
//    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.authorizeHttpRequests().antMatchers("/sample/all").permitAll()
            .antMatchers("/sample/member").hasRole("USER")
        http.formLogin()
        http.csrf().disable()
        //
        http.logout()
        http.oauth2Login().successHandler(successHandler())
        http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService)
//        http.addFilterBefore(apiCheckFilter(),
//            UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
    @Bean
    fun successHandler(): ClubLoginSuccessHandler {//config 수정
        return ClubLoginSuccessHandler(passwordEncoder = passwordEncoder())
    }
    @Bean
    fun apiCheckFilter():ApiCheckFilter{
        return ApiCheckFilter(pattern = "/notes/**/*")
    }
}