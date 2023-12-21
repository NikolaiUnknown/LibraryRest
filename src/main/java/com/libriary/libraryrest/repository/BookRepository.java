package com.libriary.libraryrest.repository;

import com.libriary.libraryrest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT books.* FROM UserBooks JOIN books ON UserBooks.BookId = books.id WHERE UserBooks.UserId = :userid", nativeQuery = true)
    List<Book> listOfTaken(long userid);

    Optional<Book> findById(int id);

    @Modifying
    @Transactional
    @Query(value = "Update books set isTaken = true where id=:bookid" ,nativeQuery = true)
    void takedbook(int bookid);
    @Modifying
    @Transactional
    @Query(value = "Update books set isTaken = false where id=:bookid" ,nativeQuery = true)
    void returnedbook(int bookid);
}
