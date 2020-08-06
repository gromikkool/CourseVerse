package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.AddressRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl extends AbstractRepository<Address, Integer> implements AddressRepository {

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }
}
