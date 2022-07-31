package org.zerock.guestbook.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.zerock.guestbook.entity.Guestbook
import org.zerock.guestbook.entity.QGuestbook
import java.util.Optional
import java.util.stream.IntStream

@SpringBootTest
class GuestbookRepositoryTests(
    @Autowired
    val guestbookRepository: GuestbookRepository){

    @Test
    fun insertDummies(){
        IntStream.rangeClosed(1,300).forEach{
            val guestbook=Guestbook(title = "Title....${it}", content = "Content...${it}", writer = "user${it%10}")
            println(guestbookRepository.save(guestbook))
        }
    }

    @Test
    fun updateTest(){
        val result:Optional<Guestbook> = guestbookRepository.findById(300L)

        if(result.isPresent){
            val guestbook:Guestbook=result.get()

            guestbook.changeContent("Changed Content")
            guestbook.changeTitle("Changed title....")

            guestbookRepository.save(guestbook)
        }
    }

    @Test
    fun testQuery1(){
        val pageable:Pageable =PageRequest.of(0,10, Sort.by("gno").descending())

        val qGuestbook:QGuestbook=QGuestbook.guestbook//1
        val keyword:String="1"

        val builder:BooleanBuilder= BooleanBuilder()//2

        val expression:BooleanExpression= qGuestbook.title.contains(keyword)//3

        builder.and(expression)//4

        val result:Page<Guestbook> = guestbookRepository.findAll(builder,pageable)//5

        result.stream().forEach{
            println(it)
        }
    }

    @Test
    fun testQuery2(){
        val pageable:Pageable =PageRequest.of(0,10, Sort.by("gno").descending())
        val qGuestbook:QGuestbook=QGuestbook.guestbook
        val keyword:String="1"
        val builder:BooleanBuilder=BooleanBuilder()

        val exTitle:BooleanExpression=qGuestbook.title.contains(keyword)

        val exContent:BooleanExpression=qGuestbook.content.contains(keyword)

        val exAll:BooleanExpression=exTitle.or(exContent)//1-----

        builder.and(exAll)//2---

        builder.and(qGuestbook.gno.gt(0L))//3------

        val result:Page<Guestbook> = guestbookRepository.findAll(builder,pageable)//5

        result.stream().forEach{
            println(it)
        }

    }
}