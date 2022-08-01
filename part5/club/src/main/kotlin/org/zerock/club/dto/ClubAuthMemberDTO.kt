package org.zerock.club.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User



class ClubAuthMemberDTO(
    username:String, password:String,  var fromSocial:Boolean,
    authorities: MutableCollection<out GrantedAuthority>?,
    var name:String?=null,
    var email:String?=null): User(username,password,authorities){
    init {
        this.email=username
    }
}