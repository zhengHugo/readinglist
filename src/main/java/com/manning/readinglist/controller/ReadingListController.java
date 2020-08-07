package com.manning.readinglist.controller;

import com.manning.readinglist.model.Book;
import com.manning.readinglist.repositories.ReadingListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ReadingListController {

    private final ReadingListRepository readingListRepository;

    @GetMapping("/{reader}")
    public String readerBooks(@PathVariable String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @PostMapping("/{reader}")
    public String addToReadingList(@PathVariable String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
