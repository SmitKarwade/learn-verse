package com.example.learnverse.activity.service;

import com.example.learnverse.activity.model.Activity;
import com.example.learnverse.activity.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public Activity createActivityByTutor(Activity activity, String tutorId) {
        // Optionally: check tutor's role from security context/session
        activity.setTutorId(tutorId);
        activity.setCreatedAt(new java.util.Date());
        activity.setUpdatedAt(new java.util.Date());
        activity.setIsActive(true);

        return activityRepository.save(activity);
    }
}

