package bank.country;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/country")
public class CountryController {

	private final CountryService countryService;
	
	@Autowired
    private ApplicationContext appContext;
	
	@Autowired
	public CountryController(final CountryService service) {
		this.countryService = service;
	}

	@GetMapping
	public ResponseEntity<List<Country>> findAll() {
		return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Country country) {
		country.setId(null);
		countryService.save(country);
	}
	
	

	@DeleteMapping("/{countryId}/")
	public void deleteCountry(@PathVariable("countryId") Long countryId){
		countryService.delete(countryId);
	}
	
	

	@PostMapping("/search")
	public List<Country> search(@RequestBody Country country){
		return countryService.search(country);
	}
	
	@PutMapping("/update")
	public  Country update(@RequestBody Country country) {
		return countryService.save(country);
	}
	
	/*@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	@ResponseBody 
	public void getFile(HttpServletResponse response) {
		 try {           
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "root");
		       
			JasperReport jasperReport = JasperCompileManager.compileReport("report1.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection); 
			JasperExportManager.exportReportToPdfFile(jasperPrint, "D://Test.pdf");
			
			
			
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			    try {
			        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			    } catch (JRException e1) {
			        e1.printStackTrace();
			    }
			    byte[] data = out.toByteArray();
			    response.setContentType("application/pdf");
			    response.setContentLength(data.length);
			    response.getOutputStream().write(data);
		        response.getOutputStream().flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void sendPdfResponse(HttpServletResponse response, JasperPrint jasperPrint, String fileName){

	    //Remove all whitespace from file name
	    fileName.replaceAll("\\s","");

	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try {
	        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	    } catch (JRException e1) {
	        e1.printStackTrace();
	    }

	    byte[] data = out.toByteArray();

	    response.setContentType("application/pdf");
	    //To make it a download change "inline" to "attachment"
	    response.setHeader("Content-disposition", "inline; filename=" + fileName + ".pdf");
	    response.setContentLength(data.length);

	    try {
	        response.getOutputStream().write(data);
	        response.getOutputStream().flush();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
}
	
	
	