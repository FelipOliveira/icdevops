package com.foliveira.icdevops.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.foliveira.icdevops.model.Item;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemRepositoryTest {
    
    @Autowired
    private ItemRepository repository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void ChecaGravarNovoItem(){
        Item item = Item.builder()
                .nome("abacaxi")
                .preco(new BigDecimal(12))
                .quantidade(1)
                .build();
        repository.save(item);
        Assertions.assertThat(item.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void ChecaDevolverItem(){
        Item item = repository.findById(1L).get();
        Assertions.assertThat(item.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void ChecaDevolverListaItens(){
        List<Item> itens = repository.findAll();
        Assertions.assertThat(itens.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void ChecaEditarItem(){
        Item item = repository.findById(1L).get();
        item.setNome("maçã");
        Item itemEditado =  repository.save(item);
        Assertions.assertThat(itemEditado.getNome()).isEqualTo("maçã");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void ChecaDeletarItem(){
        Item item = repository.findById(1L).get();
        repository.delete(item);
        Item item1 = null;

        Optional<Item> optionalItem = repository.findById(1L);
        if(optionalItem.isPresent()){
            item1 = optionalItem.get();
        }
        Assertions.assertThat(item1).isNull();
    }
}