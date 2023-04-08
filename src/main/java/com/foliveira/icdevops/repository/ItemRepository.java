package com.foliveira.icdevops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foliveira.icdevops.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>  {

}
