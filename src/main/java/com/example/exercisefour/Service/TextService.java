package com.example.exercisefour.Service;

import com.example.exercisefour.Document.Text;
import com.example.exercisefour.Repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextService {
    private final TextRepository textRepository;

    @Autowired
    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public void save(final Text text) {
        textRepository.save(text);
    }

    public List<Text> searchAllText(String text) {
        return textRepository.findAllByTextContainingIgnoreCase(text);
    }

    public void delete(String id) {
        textRepository.deleteById(id);
    }
}