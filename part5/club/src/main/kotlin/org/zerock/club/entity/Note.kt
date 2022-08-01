package org.zerock.club.entity


import org.zerock.guestbook.entity.BaseEntity
import javax.persistence.*

@Entity
data class Note(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val num:Long,
    var title:String,var content:String,
    @ManyToOne(fetch = FetchType.LAZY)
    val writer:ClubMember): BaseEntity() {

    fun changeTitle(title: String){
        this.title=title
    }

    fun changeContent(content:String){
        this.content=content
    }
}