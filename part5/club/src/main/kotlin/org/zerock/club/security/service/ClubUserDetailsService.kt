package org.zerock.club.security.service

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.zerock.club.dto.ClubAuthMemberDTO
import org.zerock.club.entity.ClubMember
import org.zerock.club.repository.ClubMemberRepository
import java.util.*
import java.util.stream.Collectors

@Service
class ClubUserDetailsService(private val clubMemberRepository: ClubMemberRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails?{
        println("ClubUserDetailsService loadUserByUsername $username")

        val result: Optional<ClubMember> = clubMemberRepository.findByEmail(username!!,false)

        if(!result.isPresent){
            throw UsernameNotFoundException("Check email or Social")
        }

        val clubMember=result.get()

        println("----------------------------------")
        println(clubMember)

        val clubAuthMember: ClubAuthMemberDTO = ClubAuthMemberDTO(
            clubMember.email,clubMember.password, clubMember.fromSocial!!,
            clubMember.roleSet.stream().map {
                SimpleGrantedAuthority("ROLE_"+it.name)
            }.collect(Collectors.toSet())
        )

        clubAuthMember.name=clubMember.name
        clubAuthMember.fromSocial= clubMember.fromSocial!!

        return clubAuthMember
    }
}