package org.zerock.club.controller

import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.zerock.club.dto.ClubAuthMemberDTO

@Controller
@RequestMapping("/sample")
class SampleController {
    private val log = LoggerFactory.getLogger(javaClass)
    @GetMapping("/all")
    fun exAll(){
        log.info("exAll...")
    }

    @GetMapping("/member")
    fun exMember(@AuthenticationPrincipal clubAuthMember: ClubAuthMemberDTO){
        log.info("exMember...")

        log.info("-------------------------")
        log.info(clubAuthMember.toString())
    }
    @GetMapping("/admin")
    fun exAdmin(){
        log.info("exAdmin...")
    }
}