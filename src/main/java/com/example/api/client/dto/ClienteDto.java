package com.example.api.client.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ClienteDto {

	private Integer idCliente;

	private String nombreCliente;
	private String apellidoCliente;
	private String emailCliente;
	private Date nacimientoCliente;

}
