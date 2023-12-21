package com.libriary.libraryrest.service.book;

import com.libriary.libraryrest.entity.Book;
import com.libriary.libraryrest.entity.UserBooks;
import com.libriary.libraryrest.repository.BookRepository;
import com.libriary.libraryrest.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserBookRepository userBookRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserBookRepository userBookRepository) {
        this.bookRepository = bookRepository;
        this.userBookRepository = userBookRepository;
    }
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
    public List<Book> getTakenBook(long userid){
        return bookRepository.listOfTaken(userid);
    }
    public void saveUserBook(int userid,int bookid){
        if (bookRepository.findById(bookid).isEmpty())
            return;
        if (!bookRepository.findById(bookid).get().isTaken()){
            UserBooks userBooks = new UserBooks();
            userBooks.setUserid(userid);
            userBooks.setBookid(bookid);
            userBookRepository.save(userBooks);
            if (userBookRepository.findByUseridAndBookid(userid,bookid) != null)
                bookRepository.takedbook(bookid);
        }
    }
    public Book getBook(int id){
        return bookRepository.findById(id).orElse(null);
    }
    public void returnUserBook(int userid,int bookid){
        userBookRepository.deleteByUseridAndBookid(userid,bookid);
        if (userBookRepository.findByUseridAndBookid(userid,bookid) == null)
            bookRepository.returnedbook(bookid);
    }

}
