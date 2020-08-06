package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.ProfileRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.ProfileService;
import com.senlainc.gitcourses.kashko.raman.dto.ProfileDto;
import com.senlainc.gitcourses.kashko.raman.entity.Profile;
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
public class ProfileServiceImpl implements ProfileService {

    private static final Logger LOGGER = LogManager.getLogger(ProfileServiceImpl.class);
    private ProfileRepository profileRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProfileDto getById(Integer id) throws ObjectNotFoundException {
        Profile profile = profileRepository.getById(id);
        if (profile == null) {
            throw new ObjectNotFoundException("no profile with this id");
        }
        return modelMapper.map(profile, ProfileDto.class);

    }

    @Override
    public List<ProfileDto> getAll() {
        return profileRepository.getAll().stream()
                .map(x -> modelMapper.map(x, ProfileDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ProfileDto dto) {
        Profile profile = modelMapper.map(dto, Profile.class);
        profileRepository.save(profile);
    }

    @Override
    public ProfileDto update(Integer id, ProfileDto dto) throws ObjectNotFoundException {
        Profile profile = profileRepository.getById(id);
        dto.setId(profile.getId());
        profile = modelMapper.map(dto, Profile.class);
        return modelMapper.map(profileRepository.update(profile), ProfileDto.class);
    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        profileRepository.delete(profileRepository.getById(id));
        return id;
    }
}
