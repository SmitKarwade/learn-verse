package com.example.learnverse.activity.service;

import com.example.learnverse.activity.model.Activity;
import com.example.learnverse.activity.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public Activity createActivityByTutor(Activity activity, String tutorId) {
        activity.setTutorId(tutorId);
        activity.setCreatedAt(new java.util.Date());
        activity.setUpdatedAt(new java.util.Date());
        activity.setIsActive(true);

        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivitiesForUsers() {
        return activityRepository.findByIsActiveAndIsPublic(true, true);
    }
}

