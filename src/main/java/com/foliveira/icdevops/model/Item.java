package com.foliveira.icdevops.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.javamoney.moneta.Money;

import com.foliveira.icdevops.utils.MoneyUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Item {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal preco;

    private int quantidade;

    public Item(String nome, double preco, int quantidade){
        this.nome = nome;
        this.preco = preco > 0 ? Money.of(preco, MoneyUtils.brl).getNumberStripped() : Money.of(0, MoneyUtils.brl).getNumberStripped();
        this.quantidade = quantidade > 0 ? quantidade : 0;
    }

    public Item(String nome){
        this.nome = nome;
        this.preco = Money.of(0, MoneyUtils.brl).getNumberStripped();
        this.quantidade = 0;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

	public BigDecimal getPreco() {
		return this.preco;
	}
    
    public void setPreco(BigDecimal preco){
        this.preco = Money.of(preco, MoneyUtils.brl).getNumberStripped();
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade > 0 ? quantidade : 0;
    }

}
