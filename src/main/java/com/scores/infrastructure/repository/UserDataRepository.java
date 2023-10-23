package com.scores.infrastructure.repository;

import com.scores.infrastructure.repository.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, String> {

}
