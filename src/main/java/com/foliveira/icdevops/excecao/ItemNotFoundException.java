package com.foliveira.icdevops.excecao;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(long id){
        super("Item " + id + " não encontrado.");
    }
}
