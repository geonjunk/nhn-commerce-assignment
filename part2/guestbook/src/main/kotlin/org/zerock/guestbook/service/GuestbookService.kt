package org.zerock.guestbook.service

import org.zerock.guestbook.dto.GuestbookDTO
import org.zerock.guestbook.dto.PageRequestDTO
import org.zerock.guestbook.dto.PageResultDTO
import org.zerock.guestbook.entity.Guestbook

interface GuestbookService {
    fun read(gno:Long):GuestbookDTO?
    fun register(dto:GuestbookDTO):Long?
    fun remove(gno: Long)
    fun modify(dto: GuestbookDTO)
    fun getList(requestDTO:PageRequestDTO):PageResultDTO<GuestbookDTO,Guestbook>
    fun dtoToEntity(dto:GuestbookDTO):Guestbook{//default 메서드
        val entity:Guestbook= Guestbook(dto.gno,dto.title,dto.content,dto.writer)
        return entity
    }
    fun entityToDto(entity:Guestbook):GuestbookDTO{
        val dto = GuestbookDTO(entity.gno,entity.title,entity.content,entity.writer,entity.regDate,entity.modeDate)
        return dto
    }
}