package com.libriary.libraryrest.repository;

import com.libriary.libraryrest.entity.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBooks,Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from userbooks where userid=:userid AND bookid=:bookid" ,nativeQuery = true)
    void deleteByUseridAndBookid(int userid,int bookid);


    UserBooks findByUseridAndBookid(int userid, int bookid);
}
