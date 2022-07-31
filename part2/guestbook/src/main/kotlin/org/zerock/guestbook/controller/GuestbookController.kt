package org.zerock.guestbook.controller

import lombok.extern.log4j.Log4j2
import org.springframework.boot.Banner.Mode
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.zerock.guestbook.dto.GuestbookDTO
import org.zerock.guestbook.dto.PageRequestDTO
import org.zerock.guestbook.service.GuestbookService

@Controller
@RequestMapping("/guestbook")
@Log4j2
class GuestbookController (private val service:GuestbookService){

    @GetMapping("/")
    fun index():String{
        return "redirect:/guestbook/list"
    }

    @GetMapping("/list")
    fun list(pageRequestDTO: PageRequestDTO,model:Model){//화면에서 page, size 라는 파라미터 전달하면 객체로 자동 매칭
        println("list......$pageRequestDTO")
        println(HttpStatus.CREATED.value())
        model.addAttribute("result",service.getList(pageRequestDTO))
    }

    @GetMapping("/register")
    fun register(){
        println("register get ...")
    }

    @PostMapping("/register")
    fun registerPost(dto:GuestbookDTO,redirectAttributes: RedirectAttributes):String{
        println("dto ...."+dto)

        val gno: Long? =service.register(dto)//새로추가된 엔티티번호

        redirectAttributes.addFlashAttribute("msg",gno)
        return "redirect:/guestbook/list"
    }

    @GetMapping("/read","/modify")
    fun read(gno:Long,@ModelAttribute ("requestDTO") requestDTO: PageRequestDTO,model: Model){
        println("gno : $gno")
        val dto: GuestbookDTO? =service.read(gno)
        model.addAttribute("dto",dto)
    }

    @PostMapping("/remove")
    fun remove(gno: Long,redirectAttributes: RedirectAttributes):String{
        println("gno : ${gno}")

        service.remove(gno)

        redirectAttributes.addFlashAttribute("msg",gno)

        return "redirect:/guestbook/list"
    }

    @PostMapping("/modify")
    fun modify(dto:GuestbookDTO,@ModelAttribute("requestDTO") requestDTO: PageRequestDTO
    ,redirectAttributes: RedirectAttributes):String{
        println("-----------------------")
        println("dto : ${dto}")

        service.modify(dto)
        redirectAttributes.addAttribute("page",requestDTO.page)
        redirectAttributes.addAttribute("type",requestDTO.type)
        redirectAttributes.addAttribute("keyword",requestDTO.keyword)
        redirectAttributes.addAttribute("gno",dto.gno)

        return "redirect:/guestbook/read"

    }
}