package org.zerock.guestbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class GuestbookApplication

fun main(args: Array<String>) {
    runApplication<GuestbookApplication>(*args)
}
