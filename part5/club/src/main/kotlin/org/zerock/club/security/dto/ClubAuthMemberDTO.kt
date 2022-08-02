package org.zerock.club.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.Objects


class ClubAuthMemberDTO(
    username:String, password:String, var fromSocial:Boolean,
    authorities: MutableCollection<out GrantedAuthority>?,
    var attr:MutableMap<String,Objects>?=null,
    private var name:String="",
    var email:String="",
): User(username,password,authorities),OAuth2User{
    init {
        this.email=username
    }

    override fun getName(): String {
      return name
    }

    fun setName(name:String){
        this.name=name
    }

    override fun getAttributes(): MutableMap<String, Objects>? {
        return this.attr
    }

    /*constructor(username: String,password: String,fromSocial: Boolean,
                authorities: MutableCollection<out GrantedAuthority>?,attr:Map<String,Objects>)
            :this(username,password,fromSocial,authorities){
                    this.attr=attr
                }*/

}