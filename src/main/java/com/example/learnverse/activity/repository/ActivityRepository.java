package com.example.learnverse.activity.repository;


import com.example.learnverse.activity.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    // Add custom query methods if needed (e.g., findByTutorId, findByTagsContains, etc.)
}

