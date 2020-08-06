package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.SubjectRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.SubjectService;
import com.senlainc.gitcourses.kashko.raman.dto.SubjectDto;
import com.senlainc.gitcourses.kashko.raman.entity.Subject;
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
public class SubjectServiceImpl implements SubjectService {

    private static final Logger LOGGER = LogManager.getLogger(SubjectServiceImpl.class);
    private SubjectRepository subjectRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubjectDto getById(Integer id) {
        Subject subject = subjectRepository.getById(id);
        if (subject == null) {
            throw new ObjectNotFoundException("no subject with this id");
        }
        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository.getAll().stream()
                .map(x -> modelMapper.map(x, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SubjectDto dto) {
        Subject subject = modelMapper.map(dto, Subject.class);
        subjectRepository.save(subject);
    }

    @Override
    public SubjectDto update(Integer id, SubjectDto dto) throws ObjectNotFoundException {
        Subject subject = subjectRepository.getById(id);
        dto.setId(subject.getId());
        subject = modelMapper.map(dto, Subject.class);
        return modelMapper.map(subjectRepository.update(subject), SubjectDto.class);

    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        subjectRepository.delete(subjectRepository.getById(id));
        return id;
    }
}
