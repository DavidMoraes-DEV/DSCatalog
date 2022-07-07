package com.devsuperior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;

@Repository //Ao colocar esse Annotation os Objetos da interface CategoryRepository passam a ser gerenciados pelo Spring
public interface CategoryRepository extends JpaRepository<Category, Long>{

}