package edu.ucacue.factura.infraestructura.servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.Persona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PersonaReporteService {
	
	
	@Autowired
	private PersonaRepository personaRepository;
	
	
	public String generarReporte()  throws FileNotFoundException, JRException{
		
		List<Persona> personas = personaRepository.findAll();
		File file = ResourceUtils.getFile("classpath:reportes\\Reporte_Personas.jrxml");
		
		JRBeanCollectionDataSource dataSource= new JRBeanCollectionDataSource(personas);
		
		Map<String, Object> parametros = new HashedMap();
		parametros.put("creadopor", "Sebastian Quevedo");
		
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		
		
		JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parametros,dataSource);

		JasperExportManager.exportReportToHtmlFile(jasperPrint,"c:\\Reporte_Personas.html");
		JasperExportManager.exportReportToPdfFile(jasperPrint,"c:\\Reporte_Personas.pdf");
		
		return null;
	}
	
	

}
