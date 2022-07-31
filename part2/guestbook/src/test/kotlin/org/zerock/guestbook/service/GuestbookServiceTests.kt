package org.zerock.guestbook.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.zerock.guestbook.dto.GuestbookDTO
import org.zerock.guestbook.dto.PageRequestDTO
import org.zerock.guestbook.dto.PageResultDTO
import org.zerock.guestbook.entity.Guestbook

@SpringBootTest
class GuestbookServiceTests(
    @Autowired
    val service: GuestbookService
) {

    @Test
    fun testRegister(){
        val guestbookDTO:GuestbookDTO = GuestbookDTO(title ="Sample title..",content="Sample Content..", writer = "user0")

        println(service.register(guestbookDTO))
    }

    @Test
    fun testList(){
        val pageRequestDTO:PageRequestDTO= PageRequestDTO(1,10)

        val resultDTO:PageResultDTO<GuestbookDTO,Guestbook> =service.getList(pageRequestDTO)


        println("PREV : ${resultDTO.prev}")
        println("NEXT : ${resultDTO.next}")
        println("TOTAL : ${resultDTO.totalPage}")
        println("----------------------------")
        for (guestbookDTO in resultDTO.dtoList!!){
            println(guestbookDTO)
        }
        println("=============================")
        resultDTO.pageList?.forEach {
            println(it)
        }
    }

    @Test
    fun testSearch(){
        val pageRequestDTO:PageRequestDTO= PageRequestDTO(1,10,"tc","한글")

        val resultDTO:PageResultDTO<GuestbookDTO,Guestbook> = service.getList(pageRequestDTO)

        println("PREV : ${resultDTO.prev}")
        println("NEXT : ${resultDTO.next}")
        println("TOTAL : ${resultDTO.totalPage}")

        println("-----------------------------------")

        for(guestbookDTO in resultDTO.dtoList!!){
            println(guestbookDTO)
        }

        println("---------------------")
        resultDTO.pageList?.forEach { println(it) }
    }

}