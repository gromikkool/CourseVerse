package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.CourseRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.CourseService;
import com.senlainc.gitcourses.kashko.raman.dto.CourseDto;
import com.senlainc.gitcourses.kashko.raman.dto.StatusDto;
import com.senlainc.gitcourses.kashko.raman.entity.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LogManager.getLogger(CourseServiceImpl.class);

    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CourseDto getById(Integer id) throws ObjectNotFoundException {
        Course course = courseRepository.getById(id);
        if (course == null) {
            throw new ObjectNotFoundException("no course with this id");
        }
        return modelMapper.map(course, CourseDto.class);

    }

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.getAll().stream()
                .map(x -> modelMapper.map(x, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(CourseDto dto) {
        Course course = modelMapper.map(dto, Course.class);
        courseRepository.save(course);
    }

    @Override
    public CourseDto update(Integer id, CourseDto dto) throws ObjectNotFoundException {
        Course course = courseRepository.getById(id);
        dto.setId(course.getId());
        course = modelMapper.map(dto, Course.class);
        return modelMapper.map(courseRepository.update(course), CourseDto.class);
    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        courseRepository.delete(courseRepository.getById(id));
        return id;
    }


    @Override
    public List<CourseDto> sortByAlphabet() {
        return courseRepository.sortByAlphabet().stream()
                .map(x -> modelMapper.map(x, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getAvailableCourseById(Integer id) {
        Course course = courseRepository.getAvailableCourseById(id);
        if (course == null) {
            throw new ObjectNotFoundException("no course with this id or this course unavailable");
        }
        return modelMapper.map(course, CourseDto.class);

    }

    @Override
    public List<CourseDto> getAvailableCourses() {
        return courseRepository.getAvailableCourses().stream()
                .map(x -> modelMapper.map(x, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getUnavailableCourseById(Integer id) {
        Course course = courseRepository.getUnavailableCourseById(id);
        if (course == null) {
            throw new ObjectNotFoundException("no course with this id or this course unavailable");
        }
        return modelMapper.map(course, CourseDto.class);

    }

    @Override
    public List<CourseDto> getUnavailableCourses() {
        return courseRepository.getUnavailableCourses().stream()
                .map(x -> modelMapper.map(x, CourseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    @Scheduled(cron = "0 34 15 * * ?", zone = "GMT+3")
    public void checkValidityOfCourse() {
        List<CourseDto> validityCourses = getAvailableCourses();
        for (CourseDto validityCourse : validityCourses) {
            if (validityCourse.getEndDate().before(new Date(System.currentTimeMillis()))) {
                validityCourse.setStatus(StatusDto.UNAVAILABLE);
                courseRepository.update(modelMapper.map(validityCourse, Course.class));
            }
        }
    }

}
