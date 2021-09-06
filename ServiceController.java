package p20.e20insurance.e20insurance.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import p20.e20insurance.e20insurance.Entities.Service;
import p20.e20insurance.e20insurance.Services.ServiceService;  

@Controller
public class ServiceController {
    
    // name slightly altered to avoid collision with service.
    @Autowired
    ServiceService serviceService;  

    @GetMapping("/readServices")
    public ResponseEntity<List<Service>> readServices()
    { 
        
       return serviceService.readServices();

    }

    
}
