package com.example.exercisefour.Controller;

import com.example.exercisefour.Document.Text;
import com.example.exercisefour.Service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/documents")
@RequiredArgsConstructor
public class TextRestController {
    private final TextService textService;

    @GetMapping(path = "/all", produces = "application/json")
    public Page<Text> all(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return textService.all(PageRequest.of(
                page.orElse(0), 1,
                Sort.Direction.ASC, sortBy.orElse("id")));
    }

}
