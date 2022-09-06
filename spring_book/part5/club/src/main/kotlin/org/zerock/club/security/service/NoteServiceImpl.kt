package org.zerock.club.security.service

import org.springframework.stereotype.Service
import org.zerock.club.security.dto.NoteDTO
import org.zerock.club.entity.Note
import org.zerock.club.repository.NoteRepository
import java.util.Optional
import java.util.stream.Collectors

@Service
class NoteServiceImpl(private val noteRepository: NoteRepository): NoteService {

    override fun register(noteDTO: NoteDTO): Long {
        val note = dtoToEntity(noteDTO)

        println("===========================================")
        println(note)

        noteRepository.save(note)

        return note.num
    }

    override fun get(num: Long): NoteDTO? {
        val result:Optional<Note> = noteRepository.getWithWriter(num)

        if(result.isPresent){
            return entityToDTO(result.get())
        }
        return null
    }

    override fun modify(noteDTO: NoteDTO) {
        val num = noteDTO.num

        val result:Optional<Note> = noteRepository.findById(num)

        if(result.isPresent){
            val note:Note=result.get()

            note.changeTitle(noteDTO.title)
            note.changeContent(noteDTO.content)
            noteRepository.save(note)
        }
    }

    override fun remove(num: Long) {
        noteRepository.deleteById(num)
    }

    override fun getAllWithWriter(writerEmail: String): List<NoteDTO> {
        val noteList:List<Note> = noteRepository.getList(writerEmail)

        return noteList.stream().map {
            entityToDTO(it)
        }.collect(Collectors.toList())
    }
}