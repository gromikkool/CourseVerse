package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.AddressRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.AddressService;
import com.senlainc.gitcourses.kashko.raman.dto.AddressDto;
import com.senlainc.gitcourses.kashko.raman.entity.Address;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDto getById(Integer id) {
        Address address = addressRepository.getById(id);
        if (address == null) {
            throw new ObjectNotFoundException("not address with this id");
        }
        return modelMapper.map(address, AddressDto.class);

    }

    @Override
    public List<AddressDto> getAll() {
        return addressRepository.getAll().stream()
                .map(x -> modelMapper.map(x, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(AddressDto dto) {
        Address address = modelMapper.map(dto, Address.class);
        addressRepository.save(address);
        LOGGER.info("Successfully save");
    }

    @Override
    public AddressDto update(Integer id, AddressDto dto) throws ObjectNotFoundException {
        dto.setId(id);
        Address address = addressRepository.getById(id);
        modelMapper.map(dto, Address.class);
        address.setId(id);
        return modelMapper.map(addressRepository.update(address), AddressDto.class);
    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        addressRepository.delete(addressRepository.getById(id));
        return id;
    }
}
