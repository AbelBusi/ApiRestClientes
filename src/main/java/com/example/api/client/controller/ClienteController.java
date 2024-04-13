package com.example.api.client.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.api.client.Cliente;
import com.example.api.client.dto.ClienteDto;
import com.example.api.client.pyloard.MensajeResponse;
import com.example.api.client.service.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@PostMapping("/cliente")
	public ResponseEntity<?>  crearCliente(@RequestBody ClienteDto clienteDto) {
		Cliente clienteSave = null;
		try {

			clienteSave = clienteService.guardar(clienteDto);
			
			return new ResponseEntity<>( MensajeResponse.builder()
					.mensaje("Cliente guardado correctamente")
					.object(ClienteDto.builder()
							.idCliente(clienteSave.getIdCliente())
							.nombreCliente(clienteSave.getNombreCliente())
							.apellidoCliente(clienteSave.getApellidoCliente())
							.emailCliente(clienteSave.getEmailCliente())
							.nacimientoCliente(clienteSave.getNacimientoCliente())
							.build())
					.build()
					,HttpStatus.CREATED);

		} catch (DataAccessException exDT) {
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDT.getMessage())
					.object(null)
					.build(),
					HttpStatus.METHOD_NOT_ALLOWED);
			// TODO: handle exception
		}

	}

	@PutMapping("/cliente/{idCliente}")
	public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDto clientesDto, @PathVariable Integer idCliente) {

		Cliente clienteActualizar = null;
		try {
			
			
			if(clienteService.existById(idCliente)) {
				clientesDto.setIdCliente(idCliente);
				clienteActualizar = clienteService.guardar(clientesDto);
			
			return new ResponseEntity<>( MensajeResponse.builder()
					.mensaje("Informacion del cliente actualizado correctamente")
					.object(ClienteDto.builder()
							.idCliente(clienteActualizar.getIdCliente())
							.nombreCliente(clienteActualizar.getNombreCliente())
							.apellidoCliente(clienteActualizar.getApellidoCliente())
							.emailCliente(clienteActualizar.getEmailCliente())
							.nacimientoCliente(clienteActualizar.getNacimientoCliente())
							.build())
					.build()
					,HttpStatus.CREATED);
			}else {
				
				return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("El cliente no se encuentra registrado")
						.object(null)
						.build(),
						HttpStatus.METHOD_NOT_ALLOWED);
			}

		} catch (DataAccessException exDT) {
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDT.getMessage())
					.object(null)
					.build(),
					HttpStatus.METHOD_NOT_ALLOWED);
			// TODO: handle exception
		}

	}

	@DeleteMapping("/cliente/{idCliente}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idCliente) {

		try {

			Cliente clienteDelete = clienteService.findById(idCliente);
			clienteService.eliminarCliente(clienteDelete);

			return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);

		} catch (DataAccessException exDT) {

			return new ResponseEntity<>(MensajeResponse.builder().mensaje(exDT.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<?>  mostrarCliente(@PathVariable Integer idCliente) {
		Cliente cliente = clienteService.findById(idCliente);
		
		if(cliente==null) {
			
			return new ResponseEntity<>(
					MensajeResponse.builder()
					.mensaje("El cliente no existe")
							.object(null)
							.build(),
					HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<> (MensajeResponse.builder()
				.mensaje("Cliente encontrado")
				.object(ClienteDto.builder()
						.idCliente(cliente.getIdCliente())
						.nombreCliente(cliente.getNombreCliente())
						.apellidoCliente(cliente.getApellidoCliente())
						.emailCliente(cliente.getEmailCliente())
						.nacimientoCliente(cliente.getNacimientoCliente())
						.build())
				.build()
				,HttpStatus.OK);
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<?>  mostrarClientes() {
		
		List<Cliente> getList =clienteService.listCliente();
		
		
		if(getList==null) {
			
			return new ResponseEntity<>(
					MensajeResponse.builder()
					.mensaje("No hay registro de clientes")
							.object(null)
							.build(),
					HttpStatus.OK);
			
		}
		
		return new ResponseEntity<> (MensajeResponse.builder()
				.mensaje("Cliente encontrado")
				.object(getList)
				.build()
				,HttpStatus.OK);
	}

}
