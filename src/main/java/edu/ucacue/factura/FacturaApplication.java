package edu.ucacue.factura;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import edu.ucacue.factura.controlador.Principal;

@SpringBootApplication
public class FacturaApplication {

	public static void main(String[] args) throws FileNotFoundException{
		//SpringApplication.run(FacturaApplication.class, args);

		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturaApplication.class)
	    .headless(false)
	    .web(WebApplicationType.NONE)
	    .run(args);
		
		Principal p = contexto.getBean(Principal.class);
		p.GUI();
	}

}
