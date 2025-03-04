package me.deshark.lms.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import me.deshark.lms.domain.model.catalog.entity.BookMetadata;
import me.deshark.lms.domain.model.catalog.exception.BookNotFoundException;
import me.deshark.lms.domain.model.catalog.vo.Isbn;
import me.deshark.lms.domain.repository.BookRepository;
import me.deshark.lms.infrastructure.entity.BookCatalogDO;
import me.deshark.lms.infrastructure.mapper.BookCatalogMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author DE_SHARK
 */
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final BookCatalogMapper bookCatalogMapper;

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookCatalogMapper.existsByIsbn(isbn);
    }

    @Override
    public BookMetadata findByIsbn(Isbn isbn) {
        Optional<BookCatalogDO> bookCatalogDO = bookCatalogMapper.findByIsbn(isbn.toString());
        if (bookCatalogDO.isEmpty()) {
            throw new BookNotFoundException("未找到ISBN为" + isbn + "的图书");
        }
        return BookMetadata.builder()
                .isbn(isbn)
                .title(bookCatalogDO.get().getTitle())
                .author(bookCatalogDO.get().getAuthor())
                .build();
    }

    @Override
    public void save(BookMetadata bookMetadata) {
        BookCatalogDO bookCatalogDO = BookCatalogDO.builder()
                .isbn(bookMetadata.getIsbn().toString())
                .title(bookMetadata.getTitle())
                .author(bookMetadata.getAuthor())
                .build();
        bookCatalogMapper.insert(bookCatalogDO);
    }

    @Override
    public void delete(Isbn isbn) {
        bookCatalogMapper.delete(isbn.toString());
    }
}
