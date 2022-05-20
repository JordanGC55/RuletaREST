package com.ibm.academia.apirest.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder //Patron de diseño que simplifica la contruccion de objetos
public class Response {

    private String mensaje;

}
