package org.zerock.guestbook.dto


import org.springframework.http.HttpStatus
import java.time.LocalDateTime


data class GuestbookDTO(
    var gno:Long?=null,
    var title:String,
    var content:String,
    var writer:String,
    var regDate:LocalDateTime?=null,
    var modDate:LocalDateTime?=null
) {
}