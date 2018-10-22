package com.ton.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ton.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}