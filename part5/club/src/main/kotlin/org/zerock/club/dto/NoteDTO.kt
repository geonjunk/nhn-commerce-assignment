package org.zerock.club.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime


data class NoteDTO (var num:Long, var title:String, var content:String, var writerEmail:String="",
                    @DateTimeFormat(pattern = "kk:mm:ss")
                    var regDate:LocalDateTime= LocalDateTime.now(),
                    @DateTimeFormat(pattern = "kk:mm:ss")
                    var modDate:LocalDateTime=LocalDateTime.now()){

}