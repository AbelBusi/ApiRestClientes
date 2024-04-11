package com.example.api.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.client.Cliente;
import com.example.api.client.service.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@PostMapping("/cliente")
	public Cliente crearCliente(@RequestBody Cliente cliente) {

		return clienteService.guardar(cliente);

	}

	@PutMapping("/cliente")
	public Cliente actualizarCliente(@RequestBody Cliente cliente) {

		return clienteService.guardar(cliente);

	}

	@DeleteMapping("/cliente/{idCliente}")
	public void eliminar(@PathVariable Integer idCliente) {
		Cliente clienteDelete = clienteService.findById(idCliente);

		clienteService.eliminarCliente(clienteDelete);

	}

	@GetMapping("/cliente/{idCliente}")
	public Cliente mostrarCliente(@PathVariable Integer idCliente) {

		return clienteService.findById(idCliente);
	}

}
