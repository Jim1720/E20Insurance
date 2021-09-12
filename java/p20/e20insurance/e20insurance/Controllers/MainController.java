package p20.e20insurance.e20insurance.Controllers;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import p20.e20insurance.e20insurance.Services.CustomerService;
import p20.e20insurance.e20insurance.Entities.Customer;

@RestController
public class MainController {


    @Autowired
    CustomerService customerService; 

    @Autowired
    Customer cust;

    @RequestMapping(value = "/")
    public String Default()
    {
        // write a default output message to make sure server is up.
        String DEFAULT_MESSAGE = "This is E20Insurance - API server program";  
        return DEFAULT_MESSAGE; 

    };  

}
