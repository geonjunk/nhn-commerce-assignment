package org.zerock.club.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.zerock.club.entity.Note
import java.util.*

interface NoteRepository: JpaRepository<Note, Long> {
    @EntityGraph(attributePaths = ["writer"], type = EntityGraph.EntityGraphType.LOAD)
    @Query("select n from Note n where n.num=:num")
    fun getWithWriter(num:Long): Optional<Note>

    @EntityGraph(attributePaths = ["writer"], type = EntityGraph.EntityGraphType.LOAD)
    @Query("select n from Note n where n.writer.email= :email")
    fun getList(email:String):List<Note>
}