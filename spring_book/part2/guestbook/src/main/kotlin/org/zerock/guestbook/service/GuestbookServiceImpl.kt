package org.zerock.guestbook.service

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.zerock.guestbook.dto.GuestbookDTO
import org.zerock.guestbook.dto.PageRequestDTO
import org.zerock.guestbook.dto.PageResultDTO
import org.zerock.guestbook.entity.Guestbook
import org.zerock.guestbook.entity.QGuestbook
import org.zerock.guestbook.repository.GuestbookRepository
import java.util.Optional
import java.util.function.Function

@Service
class GuestbookServiceImpl(val repository: GuestbookRepository) :GuestbookService {

    override fun register(dto:GuestbookDTO) :Long?{
        println("DTO----------")
        println(dto)

        val entity:Guestbook=dtoToEntity(dto)
        println(entity)

        repository.save(entity)
        return entity.gno
    }

    override fun getList(requestDTO: PageRequestDTO): PageResultDTO<GuestbookDTO, Guestbook> {
        val pageable:Pageable=requestDTO.getPageable(Sort.by("gno").descending())

        val booleanBuilder:BooleanBuilder=getSearch(requestDTO)//검색 조건 처리

        val result:Page<Guestbook> = repository.findAll(booleanBuilder,pageable)//querydsl 사용

        val fn:Function<Guestbook,GuestbookDTO> = Function { entity -> entityToDto(entity) }
        return PageResultDTO(result,fn)
    }

    override fun read(gno: Long): GuestbookDTO? {
        val result:Optional<Guestbook> = repository.findById(gno)
        if(result.isPresent){
            return entityToDto(result.get())
        }
        return null
    }

    override fun remove(gno: Long) {
        repository.deleteById(gno)
    }

    override fun modify(dto: GuestbookDTO) {
        //제목, 내용 업데이트
        val result:Optional<Guestbook> = repository.findById(dto.gno!!)

        if(result.isPresent){
            val entity:Guestbook=result.get()

            entity.title=dto.title
            entity.content=dto.content

            repository.save(entity)
        }
    }
    fun getSearch(requestDTO: PageRequestDTO):BooleanBuilder{
        //queryDsl 처리
        val type:String?=requestDTO.type
        val booleanBuilder:BooleanBuilder= BooleanBuilder()
        val qGuestbook:QGuestbook=QGuestbook.guestbook
        val keyword:String?=requestDTO.keyword
        val expression:BooleanExpression=qGuestbook.gno.gt(0L)

        booleanBuilder.and(expression)

        if(type==null||type.trim().length===0){//검색조건 x인 경우
            return booleanBuilder
        }

        //검색 조건 작성

        val conditionBuilder:BooleanBuilder=BooleanBuilder()

        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.title.contains(keyword))
        }

        if(type.contains("c")){
            conditionBuilder.or(qGuestbook.content.contains(keyword))
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestbook.writer.contains(keyword))
        }
        booleanBuilder.and(conditionBuilder)

        return booleanBuilder
    }
}