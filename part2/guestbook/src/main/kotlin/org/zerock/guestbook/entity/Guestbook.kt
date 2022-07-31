package org.zerock.guestbook.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@NoArgsConstructor
@AllArgsConstructor
data class Guestbook(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var gno:Long?=null,
    @Column(length=100,nullable=false)
    var title:String,
    @Column(length=1500,nullable=false)
    var content:String,
    @Column(length=50,nullable=false)
    var writer:String
): BaseEntity() {

    fun changeContent(content: String){
        this.content=content
    }

    fun changeTitle(title:String){
        this.title=title
    }
}