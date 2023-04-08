package com.foliveira.icdevops.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.foliveira.icdevops.excecao.ItemNotFoundException;
import com.foliveira.icdevops.model.Item;
import com.foliveira.icdevops.repository.ItemRepository;
import com.foliveira.icdevops.utils.ItemModelAssembler;

@RestController
public class ItemController {
    
    private ItemRepository repository;
    private ItemModelAssembler assembler;

    public ItemController(ItemRepository repository, ItemModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/item")
    @ResponseBody
    public CollectionModel<EntityModel<Item>> buscaTodosOsItens() {
        List<EntityModel<Item>> itens = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(itens, linkTo(
            methodOn(ItemController.class).buscaTodosOsItens()).withSelfRel()
        );
    }
    
    @GetMapping("/item/{id}")
    @ResponseBody
    public EntityModel<Item> buscaItemPorId(@PathVariable Long id) {
        Item item = repository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(item);
    }

    @PostMapping("/item")
    @ResponseBody
    public ResponseEntity<?> novoItem(@RequestBody Item novoItem) {
        EntityModel<Item> entityModel = assembler.toModel(repository.save(novoItem));
        return ResponseEntity.created(
            entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel
        );
    }

    @PutMapping("/item/{id}")
    @ResponseBody
    public ResponseEntity<?> editarItem(@RequestBody Item novoItem, @PathVariable Long id) {
        Item itemEditado = repository.findById(id)
            .map(item -> {
              item.setNome(novoItem.getNome());
              item.setPreco(novoItem.getPreco());
              item.setQuantidade(novoItem.getQuantidade());
              return repository.save(item);
            })
            .orElseGet(() -> {
                novoItem.setId(id);
              return repository.save(novoItem);
            });
        EntityModel<Item> entityModel = assembler.toModel(itemEditado);
        return ResponseEntity 
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/item/{id}")
    @ResponseBody
    public ResponseEntity<?> deletaItem(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
