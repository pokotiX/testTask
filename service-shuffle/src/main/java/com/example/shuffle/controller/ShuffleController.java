package com.example.shuffle.controller;

import com.example.shuffle.service.ShuffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/shuffle")
public class ShuffleController {

    @Autowired
    private ShuffleService shuffleService;

    @PostMapping
    public ResponseEntity<List<Integer>> shuffle(@RequestBody Integer number) {
        if (number < 1 || number > 1000) {
            return ResponseEntity.badRequest().build();
        }

        List<Integer> shuffledNumbers = shuffleService.generateShuffledArray(number);
        shuffleService.sendRequestToLogger(number);
        return ResponseEntity.ok(shuffledNumbers);
    }
}