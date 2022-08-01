package org.zerock.club.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
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


@Configuration
class SecurityConfig {

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
        http.logout()

        http.addFilterBefore(apiCheckFilter(),
            UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun apiCheckFilter():ApiCheckFilter{
        return ApiCheckFilter(pattern = "/notes/**/*")
    }
}