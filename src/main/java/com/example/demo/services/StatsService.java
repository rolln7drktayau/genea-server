package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.StatsRepository;
import com.example.demo.entities.Stats;

import jakarta.annotation.PostConstruct;

@Service
public class StatsService {

    @Autowired
    private StatsRepository statsRepository;

    public List<Stats> getStats() {
        return statsRepository.findAll();
    }

    public Stats updateStats(Stats stats) {
        Stats toUpdate = statsRepository.findById(stats.getId()).orElse(null);
        toUpdate = stats;
        return statsRepository.save(toUpdate);
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (statsRepository.count() == 0) {
            Stats basicStats = new Stats();
            basicStats = statsRepository.save(basicStats);
        }
    }

}
