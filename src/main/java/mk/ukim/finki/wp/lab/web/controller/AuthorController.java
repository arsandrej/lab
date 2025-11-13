package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping()
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        List<Author> authors= authorService.findAll();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }
    @GetMapping("/add-form")
    public String showAddAuthorForm(Model model) {
        List<Author> authors= authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("Gender", Gender.values());
        return "author-form";
    }
    @GetMapping("/edit/{Id}")
    public String editAuthor(@PathVariable Long Id, Model model){
        Author author = authorService.findById(Id);
        model.addAttribute("author", author);
        model.addAttribute("Gender", Gender.values());

        return "author-form";
    }
    @PostMapping("/edit/{authorId}")
    public String updateAuthor(@PathVariable Long authorId,@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography,
                             @RequestParam Gender gender
                             ){
        authorService.update(authorId, name,surname,country,biography,gender);
        return "redirect:../../authors";
    }
    @PostMapping("/add")
    public String saveBook(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String country,
                           @RequestParam String biography,
                           @RequestParam Gender gender){
        Author a = new Author(name,surname,country,biography,gender);
        authorService.save(name,surname,country,biography,gender);
        return "redirect:../authors";
    }
    @GetMapping("/delete/{authorId}")
    public String deleteBook(@PathVariable Long authorId, Model model){
        authorService.deleteById(authorId);
        return "redirect:../../authors";
    }
}
