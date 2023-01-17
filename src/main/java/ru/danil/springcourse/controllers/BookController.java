package ru.danil.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.springcourse.dao.BookDAO;
import ru.danil.springcourse.dao.PersonDAO;
import ru.danil.springcourse.model.Book;
import ru.danil.springcourse.model.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO BookDAO, PersonDAO personDAO) {
        this.bookDAO = BookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent()) model.addAttribute("owner", bookOwner.get());
        else model.addAttribute("people", personDAO.index());

        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book Book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book Book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";

        bookDAO.save(Book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";

        bookDAO.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.dropBookBack(id);
        return "redirect:/book/";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.appointForPerson(id, selectedPerson);
        return "redirect:/book/";
    }
}