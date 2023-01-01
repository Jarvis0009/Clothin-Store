package com.javid.clothingstore.repository;

import com.javid.clothingstore.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    List<Reviews> findByProductId(Long id);

}
