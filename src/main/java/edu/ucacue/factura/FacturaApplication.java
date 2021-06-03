package edu.ucacue.factura;

import java.io.FileNotFoundException;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import edu.ucacue.factura.controlador.Principal;
import edu.ucacue.factura.controlador.PrincipalUI;

@SpringBootApplication
public class FacturaApplication {

	public static void main(String[] args) throws FileNotFoundException{
		//SpringApplication.run(FacturaApplication.class, args);
		/*
		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturaApplication.class)
	    .headless(false)
	    .web(WebApplicationType.NONE)
	    .run(args);
		
		Principal p = contexto.getBean(Principal.class);
		p.GUI();*/
		
		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturaApplication.class)
			    .headless(false)
			    .web(WebApplicationType.NONE)
			    .run(args);

				PrincipalUI principalUI= contexto.getBean(PrincipalUI.class);
				principalUI.setVisible(true);
	}

}
