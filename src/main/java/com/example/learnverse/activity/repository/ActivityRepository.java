package com.example.learnverse.activity.repository;


import com.example.learnverse.activity.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {

    // Find activities matching user interests
    @Query("{ $and: [ " +
            "  { $or: [ " +
            "    { 'subject': { $in: ?0 } }, " +
            "    { 'tags': { $in: ?0 } } " +
            "  ] }, " +
            "  { 'isActive': true }, " +
            "  { 'isPublic': true } " +
            "] }")
    List<Activity> findActivitiesByUserInterests(List<String> interests);

    // Fallback method
    List<Activity> findByIsActiveAndIsPublic(Boolean isActive, Boolean isPublic);
}

