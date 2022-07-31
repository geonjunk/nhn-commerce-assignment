package org.zerock.club.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.zerock.club.entity.ClubMember
import java.util.Optional

interface ClubMemberRepository : JpaRepository<ClubMember,String> {

    @EntityGraph(attributePaths = ["roleSet"], type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m where m.fromSocial= :social and m.email = :email")
    fun findByEmail(@Param("email") email:String,@Param("social") social:Boolean):Optional<ClubMember>
}