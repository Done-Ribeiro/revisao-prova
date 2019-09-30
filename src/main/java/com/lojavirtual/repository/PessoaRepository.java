package com.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojavirtual.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
