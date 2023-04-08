package com.foliveira.icdevops.model;

import static org.junit.Assert.*;

import org.javamoney.moneta.Money;
import org.junit.Test;

import com.foliveira.icdevops.utils.MoneyUtils;

public class ItemTest {
    private Item item_1 = new Item(
        "Biscoito",
        1.99,
        2
    );

    private Item item_2 = new Item(
        "leite"
    );

    @Test
    public void checaNome(){
        assertNotNull(item_1.getNome());
        assertEquals("Biscoito".toLowerCase(), item_1.getNome().toLowerCase());
        assertFalse(item_1.getNome().isEmpty());
        assertFalse(item_1.getNome().isBlank());
    }
    
    @Test
    public void checaPreco() {
        assertEquals("1.99", item_1.getPreco().toString());
        assertEquals("0", item_2.getPreco().toString());
        assertTrue(Money.of(item_1.getPreco(), MoneyUtils.brl).isGreaterThanOrEqualTo(Money.of(0, MoneyUtils.brl)));
    }

    @Test
    public void checaQuantidade(){
        assertEquals(2, item_1.getQuantidade());
        assertEquals(0, item_2.getQuantidade());
        assertTrue(item_1.getQuantidade() >= 0);
    }
}
