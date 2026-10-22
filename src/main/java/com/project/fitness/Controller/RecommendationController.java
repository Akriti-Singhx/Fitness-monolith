package com.project.fitness.Controller;


import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.Model.Recommendations;
import com.project.fitness.Service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<Recommendations> generateRecommendation(@RequestBody RecommendationRequest request){
        Recommendations recommendation = recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendations>> getUserRecommendation(@PathVariable String userId){
        List<Recommendations> recommendationsList = recommendationService.getUserRecommendation(userId);
                return ResponseEntity.ok(recommendationsList);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendations>> getActivityRecommendation(@PathVariable String activityId){
        List<Recommendations> recommendationsList = recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendationsList);
    }

}
