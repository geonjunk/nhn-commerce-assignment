package org.zerock.guestbook.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.zerock.guestbook.entity.Guestbook

interface GuestbookRepository : JpaRepository<Guestbook,Long>,QuerydslPredicateExecutor<Guestbook> {

}