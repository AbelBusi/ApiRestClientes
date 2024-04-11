package com.example.api.client.service;

import com.example.api.client.Cliente;


public interface IClienteService {
	
	Cliente guardar(Cliente cliente);
	
	Cliente findById(Integer idCliente);
	
	public void eliminarCliente(Cliente cliente);
	
}
