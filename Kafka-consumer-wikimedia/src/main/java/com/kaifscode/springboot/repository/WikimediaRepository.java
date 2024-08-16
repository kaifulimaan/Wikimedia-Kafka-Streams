package com.kaifscode.springboot.repository;

import com.kaifscode.springboot.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepository extends JpaRepository <WikimediaData,Long> {


}
