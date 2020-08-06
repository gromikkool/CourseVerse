package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.ReviewRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.ReviewService;
import com.senlainc.gitcourses.kashko.raman.dto.ReviewDto;
import com.senlainc.gitcourses.kashko.raman.entity.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LogManager.getLogger(ReviewServiceImpl.class);
    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDto getById(Integer id) throws ObjectNotFoundException {
        Review review = reviewRepository.getById(id);
        if (review == null) {
            throw new ObjectNotFoundException("no review with this id");
        }
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getAll() {
        return reviewRepository.getAll().stream()
                .map(x -> modelMapper.map(x, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ReviewDto dto) {
        Review review = modelMapper.map(dto, Review.class);
        reviewRepository.save(review);
    }

    @Override
    public ReviewDto update(Integer id, ReviewDto dto) throws ObjectNotFoundException {
        Review review = reviewRepository.getById(id);
        dto.setId(review.getId());
        review = modelMapper.map(dto, Review.class);
        return modelMapper.map(reviewRepository.update(review), ReviewDto.class);
    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        reviewRepository.delete(reviewRepository.getById(id));
        return id;
    }
}
