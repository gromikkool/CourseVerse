package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.RoleRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.RoleService;
import com.senlainc.gitcourses.kashko.raman.dto.RoleDto;
import com.senlainc.gitcourses.kashko.raman.entity.Role;
import com.senlainc.gitcourses.kashko.raman.entity.RoleEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto getByName(RoleEnum roleEnum) {
        Role role = roleRepository.getByName(roleEnum.name());
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto getById(Integer id) throws ObjectNotFoundException {
        Role role = roleRepository.getById(id);
        if (role == null) {
            throw new ObjectNotFoundException("not role with this id");
        }
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.getAll().stream()
                .map(x -> modelMapper.map(x, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(RoleDto dto) {

        Role role = modelMapper.map(dto, Role.class);
        roleRepository.save(role);
    }

    @Override
    public RoleDto update(Integer id, RoleDto dto) throws ObjectNotFoundException {
        Role role = roleRepository.getById(id);
        dto.setId(role.getId());
        role = modelMapper.map(dto, Role.class);
        return modelMapper.map(roleRepository.update(role), RoleDto.class);
    }

    @Override
    public Integer delete(Integer id) {
        roleRepository.delete(roleRepository.getById(id));
        return id;
    }
}
