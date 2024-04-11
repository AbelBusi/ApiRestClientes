package com.example.api.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.client.Cliente;
import com.example.api.client.repository.IClienteRepository;
import com.example.api.client.service.IClienteService;

@Service
public class IClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Transactional
	@Override
	public Cliente guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente findById(Integer idCliente) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(idCliente).orElse(null);
	}

	@Transactional
	@Override
	public void eliminarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteRepository.delete(cliente);
	}

}