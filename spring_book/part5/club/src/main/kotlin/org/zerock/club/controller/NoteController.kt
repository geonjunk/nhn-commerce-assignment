package org.zerock.club.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.zerock.club.security.dto.NoteDTO
import org.zerock.club.security.service.NoteService


@RestController
@RequestMapping("/notes/")
class NoteController(private val noteService: NoteService) {

    @PostMapping(value = [""])
    fun register(@RequestBody noteDTO: NoteDTO):ResponseEntity<Long>{

        println("=========register================")
        println(noteDTO)

        val num:Long = noteService.register(noteDTO)
        return ResponseEntity(num,HttpStatus.OK)
    }

    @GetMapping(value= ["/{num}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun read(@PathVariable("num") num:Long):ResponseEntity<NoteDTO>{
        println("-----------------read ------------")
        println(num)

        return ResponseEntity(noteService.get(num),HttpStatus.OK)
    }

    @GetMapping(value= ["/all"],produces= [MediaType.APPLICATION_JSON_VALUE])
    fun getList(email:String):ResponseEntity<List<NoteDTO>>{
        println("=======================getList=================")
        println(email)

        return ResponseEntity(noteService.getAllWithWriter(email),HttpStatus.OK)
    }

    @DeleteMapping(value=["/{num}"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun remove(@PathVariable("num")num: Long):ResponseEntity<String>{
        println("=========remove============")
        println(num)

        noteService.remove(num)

        return ResponseEntity("removed",HttpStatus.OK)
    }

    @PutMapping(value= ["/{num}"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun modify(@RequestBody noteDTO: NoteDTO):ResponseEntity<String>{
        println("==========modify============")
        println(noteDTO)

        noteService.modify(noteDTO)

        return ResponseEntity("modified",HttpStatus.OK)
    }
}