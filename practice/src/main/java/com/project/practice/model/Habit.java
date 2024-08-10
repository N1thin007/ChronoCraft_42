package com.project.practice.model;

import java.util.Arrays;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String name;
    @ElementCollection
    private Boolean[] completedCounts = new Boolean[7]; // Array for 7 days
    private int stars;

    public Habit() {
        Arrays.fill(completedCounts, false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean[] getCompletedCounts() {
        return completedCounts;
    }

    public void setCompletedCounts(Boolean[] completedCounts) {
        this.completedCounts = completedCounts;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}