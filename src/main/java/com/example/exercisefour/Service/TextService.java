package com.example.exercisefour.Service;

import com.example.exercisefour.Document.Text;
import com.example.exercisefour.Repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TextService {
    private final TextRepository textRepository;

    @Autowired
    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public void create(final Text text) {
        textRepository.save(text);
    }

    public List<Text> find() {
        List<Text> list = new ArrayList<>();
        textRepository.findAll().forEach(list::add);
        return list;
    }

    public Page<Text> find(String text,Pageable pageable) {
        return textRepository.findAllByTextContainingIgnoreCase(text, pageable);
    }
    public Page<Text> all (Pageable pageable){
        return textRepository.findAll(pageable);
    }

    public void delete(String id) {
        textRepository.deleteById(id);
    }
}