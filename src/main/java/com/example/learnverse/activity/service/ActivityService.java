package com.example.learnverse.activity.service;

import com.example.learnverse.activity.model.Activity;
import com.example.learnverse.activity.repository.ActivityRepository;
import com.example.learnverse.auth.repo.UserRepository;
import com.example.learnverse.auth.service.UserService;
import com.example.learnverse.auth.user.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserService userService;

    public Activity createActivityByTutor(Activity activity, String tutorId) {

        normalizeActivityData(activity);

        activity.setTutorId(tutorId);
        activity.setCreatedAt(new java.util.Date());
        activity.setUpdatedAt(new java.util.Date());
        activity.setIsActive(true);

        return activityRepository.save(activity);
    }

    public List<Activity> getActivitiesForUser(String userId) {
        AppUser user = userService.getUserById(userId);

        // Check if user has interests
        if (user.getInterests() == null || user.getInterests().isEmpty()) {
            throw new RuntimeException("User must add interests first to see personalized activities");
        }

        log.info("Fetching activities for user: {} with interests: {}",
                userId, user.getInterests());

        List<Activity> activities = activityRepository
                .findActivitiesByUserInterests(user.getInterests());

        log.info("Found {} activities matching user interests", activities.size());
        return activities;
    }

    public List<Activity> getAllActivitiesForUsers() {
        return activityRepository.findByIsActiveAndIsPublic(true, true);
    }

    private void normalizeActivityData(Activity activity) {
        // Normalize subject
        if (activity.getSubject() != null) {
            activity.setSubject(activity.getSubject().toLowerCase().trim());
        }

        // Normalize tags
        if (activity.getTags() != null) {
            List<String> normalizedTags = activity.getTags().stream()
                    .filter(tag -> tag != null && !tag.trim().isEmpty())
                    .map(tag -> tag.toLowerCase().trim())
                    .collect(Collectors.toList());
            activity.setTags(normalizedTags);
        }
    }
}

