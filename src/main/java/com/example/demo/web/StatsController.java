package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Stats;
import com.example.demo.services.StatsService;

//@Controller
@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "http://localhost:4200")
public class StatsController {

    @Autowired
    private StatsService statsService;
    
    @GetMapping("/")
    public List<Stats> getAllStats() {
	return statsService.getStats();
    }
    
    @PostMapping("/update")
    public ResponseEntity<Stats> update(@RequestBody Stats stats) {
	Stats savedStats = statsService.updateStats(stats);
	return new ResponseEntity<>(savedStats, HttpStatus.OK);
    }
}
