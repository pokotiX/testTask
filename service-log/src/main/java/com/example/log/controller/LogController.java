package com.example.log.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log")
public class LogController {

    @PostMapping
    public void logRequest(@RequestBody Integer requestPayload) {
        System.out.println("Received request to log: " + requestPayload);
    }
}