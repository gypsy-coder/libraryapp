package com.gp.library.repository;

import com.gp.library.model.dao.LibrariesDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrariesRepository extends JpaRepository<LibrariesDao, Integer> {

}
