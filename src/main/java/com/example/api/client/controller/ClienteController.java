package com.example.api.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.client.Cliente;
import com.example.api.client.dto.ClienteDto;
import com.example.api.client.service.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDto crearCliente(@RequestBody ClienteDto clienteDto) {
		Cliente clienteSave = clienteService.guardar(clienteDto);

		return ClienteDto.builder()
				.idCliente(clienteSave.getIdCliente()).
				nombreCliente(clienteSave.getNombreCliente())
				.apellidoCliente(clienteSave.getApellidoCliente())
				.emailCliente(clienteSave.getEmailCliente())
				.nacimientoCliente(clienteSave.getNacimientoCliente()).build();

	}

	@PutMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDto actualizarCliente(@RequestBody ClienteDto clientesDto) {

		Cliente clienteActualizar = clienteService.guardar(clientesDto);

		return ClienteDto.builder()
				.idCliente(clienteActualizar.getIdCliente())
				.nombreCliente(clienteActualizar.getNombreCliente())
				.apellidoCliente(clienteActualizar.getApellidoCliente())
				.emailCliente(clienteActualizar.getEmailCliente())
				.nacimientoCliente(clienteActualizar.getNacimientoCliente())
				.build();

	}

	@DeleteMapping("/cliente/{idCliente}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idCliente) {

		Map<String, Object> response = new HashMap<>();
		try {

			Cliente clienteDelete = clienteService.findById(idCliente);
			clienteService.eliminarCliente(clienteDelete);

			return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);

		} catch (DataAccessException exDT) {

			response.put("mensaje", exDT.getMessage());
			response.put("cliente", null);

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cliente/{idCliente}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteDto mostrarCliente(@PathVariable Integer idCliente) {
		Cliente cliente = clienteService.findById(idCliente);
		return ClienteDto.builder()
				.idCliente(cliente.getIdCliente())
				.nombreCliente(cliente.getNombreCliente())
				.apellidoCliente(cliente.getApellidoCliente())
				.emailCliente(cliente.getEmailCliente())
				.nacimientoCliente(cliente.getNacimientoCliente())
				.build();
	}

}
