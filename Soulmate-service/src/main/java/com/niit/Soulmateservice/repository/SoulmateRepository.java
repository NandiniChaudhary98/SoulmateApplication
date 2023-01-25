package com.niit.Soulmateservice.repository;

import com.niit.Soulmateservice.domain.SoulmateUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoulmateRepository extends MongoRepository<SoulmateUser,String> {
    public SoulmateUser findBygender(String gender);
    public SoulmateUser findBycity(String city);
}
