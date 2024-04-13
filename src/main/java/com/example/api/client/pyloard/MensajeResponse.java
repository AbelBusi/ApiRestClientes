package com.example.api.client.pyloard;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MensajeResponse {

	private String mensaje;
	private Object object;
}
