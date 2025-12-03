package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    BookService bookService;
    AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        List<Book> books= bookService.listAll();
        model.addAttribute("books", books);
        List<Author> authors= authorService.findAll();
        model.addAttribute("authors", authors);
        return "listBooks";
    }

    @GetMapping("/add-form")
    public String showAddBookForm(Model model) {
        List<Author> authors= authorService.findAll();
        model.addAttribute("authors", authors);
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){

        bookService.save(title,genre,averageRating,authorId);
        return "redirect:../books";
    }

    @GetMapping("/edit/{Id}")
    public String editBook(@PathVariable Long Id, Model model){
        Book book = bookService.getById(Id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/edit/{bookId}")
    public String updateBook(@PathVariable Long bookId,@RequestParam String title,
                             @RequestParam String genre,
                             @RequestParam Double averageRating,
                             @RequestParam Long authorId){
        Author author = authorService.findById(authorId);
        bookService.update(bookId,title,genre,averageRating,authorId);
        return "redirect:../../books";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId, Model model){
        bookService.deleteById(bookId);
        return "redirect:../../books";

    }
    @PostMapping("/search")
    public String searchBooks(@RequestParam(required = false) String text,
                              @RequestParam(required = false) Double rating,
                              Model model) {
        List<Book> books;
        try {
            books = bookService.searchBooks(text, rating);
        } catch (Exception e) {
            books = bookService.listAll();
        }
        model.addAttribute("books", books);
        List<Author> authors= authorService.findAll();
        model.addAttribute("authors", authors);
        return "listBooks";
    }
    @PostMapping("/searchAuthor")
    public String searchBooksByAuthor(@RequestParam(name = "authorId", required = false) Long authorId,
                                      Model model) {
        List<Book> books;

        if (authorId == null || authorId == 1234567891) {
            books = bookService.listAll();
        } else {
            books = bookService.findAllByAuthorId(authorId);
        }

        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("selectedAuthorId", authorId);

        return "listBooks";
    }


}