package org.zerock.club.security

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class PasswordTests {

    @Autowired
    private lateinit var passwordEncoder:PasswordEncoder

    @Test
    fun testEncode(){
        val password="1111"
        val enPw=passwordEncoder?.encode(password)

        println("enPw : "+enPw)

        val matchResult:Boolean= passwordEncoder?.matches(password,enPw)
        println("matchResult : "+matchResult )

    }
}