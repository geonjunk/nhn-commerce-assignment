package org.zerock.guestbook.dto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import java.util.function.Function
import java.util.stream.IntStream
import kotlin.math.ceil

data class PageResultDTO<DTO,EN> (var dtoList:List<DTO>?=null,
                                  var totalPage:Int?=null,
                                  var page:Int=0,
                                  var size:Int=0,
                                  var start:Int=0,
                                  var end:Int=0,
                                  var prev:Boolean?=null,
                                  var next:Boolean?=null,
                                  var pageList:MutableList<Int>?=null,
                                  var status:Int?= HttpStatus.CREATED.value()
){//DTO=dto 타입, EN=entity 타입
    constructor(result: Page<EN>,fn:Function<EN,DTO>):this(){
        dtoList=result.map(fn).toList()
        totalPage=result.totalPages
        makePageList(result.pageable)

    }

    private fun makePageList(pageable:Pageable){
        this.page=pageable.pageNumber+1
        this.size=pageable.pageSize
        println(page)

        val tempEnd:Int=((ceil(page/10.0))*10).toInt()
        println(tempEnd)
        start=tempEnd-9
        println(start)
        prev=start>1

        next=totalPage!!>tempEnd

        if(totalPage!! >tempEnd) end=tempEnd
        else end=totalPage!!

        pageList=IntStream.rangeClosed(start,end).boxed().toList()

    }
}