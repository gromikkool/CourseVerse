package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.LessonRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.LessonService;
import com.senlainc.gitcourses.kashko.raman.dto.LessonDto;
import com.senlainc.gitcourses.kashko.raman.dto.StatusDto;
import com.senlainc.gitcourses.kashko.raman.entity.Lesson;
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
public class LessonServiceImpl implements LessonService {

    private static final Logger LOGGER = LogManager.getLogger(LessonServiceImpl.class);
    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LessonDto getById(Integer id) throws ObjectNotFoundException {
        Lesson lesson = lessonRepository.getById(id);
        if (lesson == null) {
            throw new ObjectNotFoundException("no lesson with this id");
        }
        return modelMapper.map(lesson, LessonDto.class);

    }

    @Override
    public List<LessonDto> getAll() {
        return lessonRepository.getAll().stream()
                .map(x -> modelMapper.map(x, LessonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(LessonDto dto) {
        Lesson lesson = modelMapper.map(dto, Lesson.class);
        lessonRepository.save(lesson);
    }

    @Override
    public LessonDto update(Integer id, LessonDto dto) throws ObjectNotFoundException {
        Lesson lesson = lessonRepository.getById(id);
        dto.setId(lesson.getId());
        lesson = modelMapper.map(dto, Lesson.class);
        return modelMapper.map(lessonRepository.update(lesson), LessonDto.class);
    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        lessonRepository.delete(lessonRepository.getById(id));
        return id;
    }

    @Override
    public List<LessonDto> sortByAlphabet() {
        return lessonRepository.sortByAlphabet().stream()
                .map(x -> modelMapper.map(x, LessonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonDto> getAvailableLessons() {
        return lessonRepository.getAvailableLessons().stream()
                .map(x -> modelMapper.map(x, LessonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(cron = "0 34 15 * * ?", zone = "GMT+3")
    public void checkValidityOfLessons() {
        List<LessonDto> validityLessons = getAvailableLessons();
        for (LessonDto validityLesson : validityLessons) {
            if (validityLesson.getEndTime().before(new Date(System.currentTimeMillis()))) {
                validityLesson.setStatus(StatusDto.UNAVAILABLE);
                lessonRepository.update(modelMapper.map(validityLesson, Lesson.class));
            }
        }
    }
}
