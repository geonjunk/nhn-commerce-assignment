package org.zerock.club.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/sample")
class SampleController {
    private val log = LoggerFactory.getLogger(javaClass)
    @GetMapping("/all")
    fun exAll(){
        log.info("exAll...")
    }

    @GetMapping("/member")
    fun exMember(){
        log.info("exMember...")
    }

    @GetMapping("/admin")
    fun exAdmin(){
        log.info("exAdmin...")
    }
}