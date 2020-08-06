package com.senlainc.gitcourses.kashko.raman.controller;


import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.ReviewService;
import com.senlainc.gitcourses.kashko.raman.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(value = "/{id}")
    public ReviewDto getReviewById(@PathVariable Integer id) {
        return reviewService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@RequestBody ReviewDto reviewDto) {
        reviewService.save(reviewDto);
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteReviewById(@PathVariable Integer id) throws ObjectNotFoundException {
        return reviewService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public ReviewDto updateReview(@PathVariable(name = "id") Integer id, @RequestBody ReviewDto reviewDto) throws ObjectNotFoundException {
        return reviewService.update(id, reviewDto);
    }
}
