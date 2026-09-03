package com.example.manager_library.service;

import com.example.manager_library.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.manager_library.repository.IBookRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService{

    private final IBookRepository bookRepository;
    @Override
    public Page<Book> getAllBook(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBook(int id) {
        return bookRepository.findBookById(id);
    }

    @Override
    @Transactional
    public Book save(Book book){
        return bookRepository.save(book);
    }
}
