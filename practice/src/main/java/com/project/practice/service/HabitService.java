package com.project.practice.service;

import com.project.practice.model.Habit;
import com.project.practice.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {
    @Autowired
    private HabitRepository habitRepository;

    public List<Habit> getHabitsByUserId(String userId) {
        return habitRepository.findByUserId(userId);
    }

    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }
}