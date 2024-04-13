package com.example.api.client.service;

import com.example.api.client.Cliente;
import com.example.api.client.dto.ClienteDto;


public interface IClienteService {
	
	Cliente guardar(ClienteDto cliente);
	
	Cliente findById(Integer idCliente);
	
	public void eliminarCliente(Cliente cliente);
	
	boolean existById (Integer id);
	
}
