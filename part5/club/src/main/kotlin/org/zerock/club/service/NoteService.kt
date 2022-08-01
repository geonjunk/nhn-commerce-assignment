package org.zerock.club.service

import org.zerock.club.dto.NoteDTO
import org.zerock.club.entity.ClubMember
import org.zerock.club.entity.Note

interface NoteService {

    fun register(noteDTO: NoteDTO):Long
    fun get(num:Long):NoteDTO?
    fun modify(noteDTO: NoteDTO)
    fun remove(num:Long)

    fun getAllWithWriter(writerEmail:String):List<NoteDTO>


    fun dtoToEntity(noteDTO: NoteDTO):Note{
        val note: Note= Note(noteDTO.num,noteDTO.title,noteDTO.content,
            ClubMember(email=noteDTO.writerEmail))

        return note
    }

    fun entityToDTO(note:Note):NoteDTO{
        val noteDTO=NoteDTO(note.num,note.title,note.content,note.writer.email,note.regDate!!,note.modeDate!!)
        return noteDTO
    }
}