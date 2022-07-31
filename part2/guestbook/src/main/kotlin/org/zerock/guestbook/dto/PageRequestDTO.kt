package org.zerock.guestbook.dto

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

data class PageRequestDTO(var page:Int=1,var size:Int=10,var type:String?=null,var keyword:String?=null) {
    fun getPageable(sort:Sort):Pageable{//JPA에서 사용하는 Pageable 객체 생성
        return PageRequest.of(page-1,size,sort)
    }
}