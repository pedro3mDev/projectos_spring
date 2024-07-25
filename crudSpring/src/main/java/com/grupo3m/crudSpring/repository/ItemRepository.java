package com.grupo3m.crudSpring.repository;

import com.grupo3m.crudSpring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
