package org.zerock.club.entity

import org.zerock.guestbook.entity.BaseEntity
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

@Entity
data class ClubMember(@Id
                      private var email:String,
                      private var password:String,
                      private var name :String,private var fromSocial:Boolean,
                     @ElementCollection(fetch = FetchType.LAZY)
                     val roleSet:MutableSet<ClubMemberRole> = HashSet()):BaseEntity() {

                         fun addMemberRole(clubMemberRole:ClubMemberRole){
                             roleSet.add(clubMemberRole)
                         }


}