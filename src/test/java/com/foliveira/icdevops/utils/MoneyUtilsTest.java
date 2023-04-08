package com.foliveira.icdevops.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import com.foliveira.utils.MoneyUtils;

public class MoneyUtilsTest {
    
    @Test
    public void checaSeDinheiroExiste() {
        assertNotNull(MoneyUtils.brl);
        assertEquals(MoneyUtils.brl.getCurrencyCode(), "BRL");
        assertEquals(MoneyUtils.brl.getNumericCode(), 986);
        assertEquals(MoneyUtils.brl.getDefaultFractionDigits(), 2);
    }
    
}
