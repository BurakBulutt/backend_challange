package com.example.example_6.repository;

import com.example.example_6.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Team sinifinin data access katmani.(repository interfacesi)
 */
@Repository
public interface TeamRepository extends JpaRepository<Team,String> {

}


