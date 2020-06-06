package com.gp.library.repository;

import com.gp.library.model.dao.BooksDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<BooksDao, Integer>{

    @Query("from BooksDao dao where dao.library.libraryId=:id")
    List<BooksDao> getBooksByLibraryId(@Param("id")int id);
}
