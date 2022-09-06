package org.zerock.club.security.service

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.zerock.club.dto.ClubAuthMemberDTO
import org.zerock.club.entity.ClubMember
import org.zerock.club.entity.ClubMemberRole
import org.zerock.club.repository.ClubMemberRepository
import java.util.*
import java.util.stream.Collectors

@Service
class ClubOAuth2UserDetailsService(val repository: ClubMemberRepository,
                                   val passwordEncoder: PasswordEncoder
) :DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        println("=============================================")
        println("userRequest : $userRequest")

        val clientName:String= userRequest!!.clientRegistration.clientName

        println("clientName : $clientName")
        println(userRequest.additionalParameters)

        val oAuth2User:OAuth2User = super.loadUser(userRequest)

        println("=======================================")
        oAuth2User.attributes.forEach { k, v ->
            println("$k : $v")
        }

        var email:String?=null

        if(clientName == "Google"){
            email= oAuth2User.attributes["email"].toString()
        }

        println("Email : $email")

//        val member:ClubMember= saveSocialMember(email!!)
//        return oAuth2User

        val member:ClubMember=saveSocialMember(email!!)

        val clubAuthMember: ClubAuthMemberDTO = ClubAuthMemberDTO(member.email,
            member.password,true,member.roleSet.stream().map {
                SimpleGrantedAuthority("ROLE_${it.name}")
            }.collect(Collectors.toList()), oAuth2User.attributes as MutableMap<String, Objects>?
        )
        clubAuthMember.name = member.name

        return clubAuthMember
    }
    fun saveSocialMember(email:String):ClubMember{
        val result: Optional<ClubMember> = repository.findByEmail(email,true)

        if(result.isPresent){
            return result.get()
        }

        //없다면 새로 추가
        val clubMember:ClubMember=
            ClubMember(name = email, email=email, password = passwordEncoder.encode("1111"), fromSocial = true)

        clubMember.addMemberRole(clubMemberRole = ClubMemberRole.USER)

        repository.save(clubMember)
        return clubMember

    }
}