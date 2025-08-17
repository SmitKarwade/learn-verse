package com.example.learnverse.activity.repository;


import com.example.learnverse.activity.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    // Find only active and public activities for users
    List<Activity> findByIsActiveAndIsPublic(Boolean isActive, Boolean isPublic);
}

