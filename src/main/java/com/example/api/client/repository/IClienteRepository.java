package com.example.api.client.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.api.client.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Integer>{

}