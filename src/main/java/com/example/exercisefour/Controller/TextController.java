package com.example.exercisefour.Controller;

import com.example.exercisefour.Document.Text;
import com.example.exercisefour.Repository.TextRepository;
import com.example.exercisefour.Service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/documents")
@RequiredArgsConstructor
public class TextController {
    private final TextService textService;
    private final TextRepository textRepository;

    @GetMapping(path = "/create", produces = "application/json")
    public ModelAndView create() {
        return new ModelAndView("create");
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ModelAndView save(
            @RequestParam String text
    ) {
        Text textToSave = new Text();
        textToSave.setText(text);
        textToSave.setId(UUID.randomUUID().toString());
        textToSave.setDateReg(LocalDateTime.now());
        textService.save(textToSave);
        return new ModelAndView("create");
    }

    @GetMapping(path = "/find", produces = "application/json")
    public ModelAndView find(
            @RequestParam(required = false) String text
    ) {
        List<Text> texts = textService.searchAllText(text);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("find");
        modelAndView.addObject("texts", texts);
        return modelAndView;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public Page<Text> all(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return textRepository.findAll(PageRequest.of(
                page.orElse(0), 1,
                Sort.Direction.ASC, sortBy.orElse("id")));
    }

    @GetMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        textService.delete(id);
    }
}
