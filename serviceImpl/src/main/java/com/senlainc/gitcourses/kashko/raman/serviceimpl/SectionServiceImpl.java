package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.SectionRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.SectionService;
import com.senlainc.gitcourses.kashko.raman.dto.SectionDto;
import com.senlainc.gitcourses.kashko.raman.entity.Section;
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
public class SectionServiceImpl implements SectionService {

    private static final Logger LOGGER = LogManager.getLogger(SectionServiceImpl.class);
    private SectionRepository sectionRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository, ModelMapper modelMapper) {
        this.sectionRepository = sectionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SectionDto getById(Integer id) throws ObjectNotFoundException {
        Section section = sectionRepository.getById(id);
        if (section == null) {
            throw new ObjectNotFoundException("not section with this id");
        }
        return modelMapper.map(section, SectionDto.class);
    }

    @Override
    public List<SectionDto> getAll() {
        return sectionRepository.getAll().stream()
                .map(x -> modelMapper.map(x, SectionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SectionDto sectionDto) {
        Section section = modelMapper.map(sectionDto, Section.class);
        sectionRepository.save(section);

    }

    @Override
    public SectionDto update(Integer id, SectionDto dto) throws ObjectNotFoundException {
        Section section = sectionRepository.getById(id);
        dto.setId(section.getId());
        section = modelMapper.map(dto, Section.class);
        return modelMapper.map(sectionRepository.update(section), SectionDto.class);
    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        sectionRepository.delete(sectionRepository.getById(id));
        return id;
    }
}
