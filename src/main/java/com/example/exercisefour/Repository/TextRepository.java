package com.example.exercisefour.Repository;

import com.example.exercisefour.Document.Text;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TextRepository extends ElasticsearchRepository<Text, String> {

    void deleteById(String id);

    Page<Text> findAllByTextContainingIgnoreCase(String text, Pageable pageable);

    Page<Text> findAll(Pageable pageable);


}