package com.project.UserRegistrationService.Repository;


import com.project.UserRegistrationService.Model.AdminRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRequestRepository extends JpaRepository<AdminRequest, Integer> {


    @Override
    public <S extends AdminRequest> S save(S entity);

    Boolean existsByEmailId(String emailId);
}
