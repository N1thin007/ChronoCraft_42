package com.project.practice.controller;

import com.project.practice.model.Habit;
import com.project.practice.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "http://localhost:3000")
public class HabitController {
    @Autowired
    private HabitService habitService;

    @GetMapping("/{userId}")
    public List<Habit> getHabits(@PathVariable String userId) {
        return habitService.getHabitsByUserId(userId);
    }

    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return habitService.saveHabit(habit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}