package org.zerock.club.security.handler

import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.zerock.club.dto.ClubAuthMemberDTO
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ClubLoginSuccessHandler(private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy(),
                              private val passwordEncoder: PasswordEncoder
) :AuthenticationSuccessHandler{



    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        println("================================================")
        println("onAuthenticationSuccess")
        val authMember: ClubAuthMemberDTO = authentication?.principal as ClubAuthMemberDTO

        val fromSocial=authMember.fromSocial

        println("Need Modify Member? $fromSocial")

        val passwordResult=passwordEncoder.matches("1111",authMember.password)

        if(fromSocial){
            redirectStrategy.sendRedirect(request,response,"/member/modify?from=social")
        }
    }
}