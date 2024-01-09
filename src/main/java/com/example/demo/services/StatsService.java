package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StatsRepository;
import com.example.demo.entities.Stats;

@Service
public class StatsService {

    @Autowired
    private StatsRepository statsRepository;
    
    public List<Stats> getStats() {
	return statsRepository.findAll();
    }

    public Stats createStats(Stats stats) {
	return statsRepository.save(stats);
    }

    public Stats updateStats(Stats stats) {
	return statsRepository.save(stats);
    }

}
