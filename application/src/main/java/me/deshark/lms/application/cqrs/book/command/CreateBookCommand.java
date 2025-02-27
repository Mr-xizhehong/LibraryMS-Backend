package me.deshark.lms.application.cqrs.book.command;

/**
 * @author DE_SHARK
 */
public record CreateBookCommand(
        String isbn,
        String title,
        String author,
        String publisher,
        int publishYear,
        String description,
        String coverImage
) {}
