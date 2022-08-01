package org.zerock.club.security

import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ApiCheckFilter(val antPathMatcher: AntPathMatcher= AntPathMatcher(),
                     var pattern:String) :OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("RequestURI: ${request.requestURI}")
        println(antPathMatcher.match(pattern,request.requestURI))

        if(antPathMatcher.match(pattern,request.requestURI)){
            println("=============API check Filter===============")
            println("=============API check Filter===============")
            println("=============API check Filter===============")

            return;
        }
        filterChain.doFilter(request,response)//다음 필터로 넘어가는 역할
    }
}