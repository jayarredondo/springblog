package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
}
