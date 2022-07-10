package com.devsuperior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Product;

@Repository //Ao colocar esse Annotation os Objetos da interface CategoryRepository passam a ser gerenciados pelo Spring
public interface ProductRepository extends JpaRepository<Product, Long>{

}