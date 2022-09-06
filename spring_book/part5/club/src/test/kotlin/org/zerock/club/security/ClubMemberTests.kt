package org.zerock.club.security

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.zerock.club.entity.ClubMember
import org.zerock.club.entity.ClubMemberRole
import org.zerock.club.repository.ClubMemberRepository
import java.util.stream.IntStream
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.Optional


@SpringBootTest
class ClubMemberTests {
    @Autowired
    private lateinit var repository:ClubMemberRepository
    @Autowired
    private lateinit var passwordEncoder:PasswordEncoder

    @Test
    fun insertDummies(){
        IntStream.rangeClosed(1,100).forEach{
            val clubMember:ClubMember=ClubMember(
                "user$it@zerock.org",
                passwordEncoder.encode("1111"),
                "사용자$it",
                false)

            clubMember.addMemberRole(clubMemberRole = ClubMemberRole.USER)

            if(it>80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER)
            }
            if(it>90){
                clubMember.addMemberRole(clubMemberRole = ClubMemberRole.ADMIN)
            }
           repository.save(clubMember)
        }
    }

    @Test
    fun testRead(){
        val result:Optional<ClubMember> = repository.findByEmail("user95@zerock.org",false)

        val clubMember:ClubMember=result.get()

        println(clubMember)

    }

}