package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.AddressService;
import com.senlainc.gitcourses.kashko.raman.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/{id}")
    public AddressDto getAddressById(@PathVariable Integer id) {
        return addressService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAddress(@RequestBody AddressDto addressDto) {
        addressService.save(addressDto);
    }

    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return addressService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteAddressById(@PathVariable Integer id) throws ObjectNotFoundException {
        return addressService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public AddressDto updateAddresses(@PathVariable(name = "id") Integer id, @RequestBody AddressDto addressDto) throws ObjectNotFoundException {
        return addressService.update(id, addressDto);
    }
}
