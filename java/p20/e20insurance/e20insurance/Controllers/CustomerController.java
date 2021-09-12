package p20.e20insurance.e20insurance.Controllers; 

 

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController; 
import p20.e20insurance.e20insurance.Services.CustomerService;
import p20.e20insurance.e20insurance.Services.TokenServices;
import p20.e20insurance.e20insurance.Entities.Customer;
import p20.e20insurance.e20insurance.Entities.PlanData;
import p20.e20insurance.e20insurance.POJO.Cust; 
import p20.e20insurance.e20insurance.POJO.ResetCustomer; 
import p20.e20insurance.e20insurance.POJO.ResetPassword;

@RestController

public class CustomerController {

    

    @Value("${E20MinutesTimeout:14}")
    String timeout; 
    
    @Value("${E20Secret:notSet}")
    String secret;   

    @Value("${E20Validate:on}")
    String validate;   

    @Autowired
    CustomerService customerService;  

    @Autowired
    Customer customer;

    /* for reset password, reset customer - custId not available */

    @Value("${E20AdminIdent:notSet}")
    String adminId;

    /* 'cust' used for 2 purposes
       1. recieves parameter from client and move to entity for updates 
       2. holds current customer for resetting to new customer
    */

    @Autowired
    Cust cust;

    @Autowired
    Customer customerHoldingArea; 

    /* requirments:

       return proper rest code 200,201,404, 500
       return customer on read calls.

    */ 
 

      // list all customers 
      @GetMapping("/listcustomers")
      public ResponseEntity<List<Customer>> ListCustomers()
      {
          // creates token and reads customer 
          return customerService.getAllCustomers();
      }
 
    // signin
    @GetMapping("/signin/{custId}")
    public ResponseEntity<Customer> signin(@PathVariable String custId)
    {
        // customer signon 
        // creates token and reads customer true = create token.
        // E20Signin header causes new token to be produced.
        
        var GET_TOKEN = true;
        return customerService.getCustomer(custId, GET_TOKEN, secret, timeout);
    }

    // read = register edit only - no duplicate 
    @GetMapping("/customer/{custId}")
     public ResponseEntity<Customer> readcustomer(@PathVariable String custId)
    {
        // customer read 

        // false no token returned.
        var NO_TOKEN = false;
        return customerService.getCustomer(custId, NO_TOKEN, "", "");
    }

    // registration - customer post -  201
    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody Cust cust)
    { 
        try
        { 
            // registration - add customer  
            CopyToEntity(cust, customer);
            return customerService.addCustomer(customer, secret, timeout);  
        }
        catch(Exception e)
        {
            var message = "Registration fails: " + e.getMessage();
            return new ResponseEntity<String>(message,null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    // customer update 200
    // EXPERIMENT : PULL FROM HEADER TOKEN NOT IMBEDDED TOKEN
    // DESIGN: WAS TO USE HEADERS; CHANGE THE CODE. JAMES.
    @PutMapping("/customer")
    public ResponseEntity<String> UpdateCustomer(@RequestBody Cust cust, @RequestHeader("E20Token") String token)
    { 
  
        try
        {  
            var ts = new TokenServices();
            var custId = cust.getCustId().trim();
            var useToken = (token == null) ? "" : token;
            var valid = ts.CheckToken(useToken, custId, secret, validate);
            if(!valid)
            {
                var message = "security: invalid token.";
                return new ResponseEntity<String>(message,null, HttpStatus.BAD_REQUEST);
                    
            }

            // customer update   
            CopyToEntity(cust, customer);
            customerService.updateCustomer(customer); 
             var message = "";
            return new ResponseEntity<String>(message,null, HttpStatus.OK);

        }

        catch (Exception e)
        { 
            var message = "Update Error: " + e.getMessage();
            return new ResponseEntity<String>(message,null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

    // plan update 200
    @PutMapping("/setplan")
    public ResponseEntity<PlanData> UpdatePlan(@RequestBody PlanData planData, @RequestHeader("E20Token") String token)
    { 
        var ts = new TokenServices();
        var custId = planData.getcustId().trim();
        var valid = ts.CheckToken(token, custId, secret, validate);
        if(!valid)
        {
            return new ResponseEntity<PlanData>(
                new PlanData(), null, HttpStatus.BAD_REQUEST); 
        }

        customerService.updatePlan(planData); 
        return new ResponseEntity<PlanData>(
            new PlanData(), null, HttpStatus.OK);
    }

    protected void CopyToEntity(Cust cust, Customer customer)
    {
        // check function naming conventions; fields must be lower in the POJO cust to map correctly.
        customer.setcustId(cust.getCustId());
        customer.setcustPassword(cust.getCustPassword());
        customer.setcustFirst(cust.getCustFirst());
        customer.setcustLast(cust.getCustLast());
        customer.setCustGender(cust.getCustGender()); 
        customer.setCustMiddle(cust.getCustMiddle());
        customer.setCustPhone(cust.getCustPhone());
        customer.setCustEmail(cust.getCustEmail());
        customer.setCustBirthDate(cust.getCustBirthDate());
        customer.setCustAddr1(cust.getCustAddr1());
        customer.setCustAddr2(cust.getCustAddr2());
        customer.setCustCity(cust.getCustCity());
        customer.setCustState(cust.getCustState());
        customer.setCustZip(cust.getCustZip()); 
        customer.setAppId(cust.getAppId());
        customer.setCustPlan(cust.getCustPlan()); 
        customer.setClaimCount(cust.getClaimCount());

    } 

    protected void CopyFromEntity(Cust cust, Customer customer)
    {
        // check function naming conventions; fields must be lower in the POJO cust to map correctly.
        cust.setcustId(customer.getcustId());
        cust.setCustpass(customer.getcustPassword());
        cust.setCustFirst(customer.getcustFirst());
        cust.setCustLast(customer.getcustLast());
        cust.setCustGender(customer.getCustGender()); 
        cust.setCustMiddle(customer.getCustMiddle());
        cust.setCustPhone(customer.getCustPhone());
        cust.setCustEmail(customer.getCustEmail());
        cust.setCustBirthDate(customer.getCustBirthDate());
        cust.setCustAddr1(customer.getCustAddr1());
        cust.setCustAddr2(customer.getCustAddr2());
        cust.setCustCity(customer.getCustCity());
        cust.setCustState(customer.getCustState());
        cust.setCustZip(customer.getCustZip()); 
        cust.setAppId(customer.getAppId());
        cust.setCustPlan(customer.getCustPlan()); 
        cust.setClaimCount(customer.getClaimCount()); 
    } 

    // - - - - - - - - - - - - - - - - - - - - - - - -
    // administator routines 
    // - - - - - - - - - - - - - - - - - - - - - - - -

    // customer list
    @GetMapping("/customerlist")
    public void ResetPassword()
    {  
        
        customerService.getAllCustomers();
    }

    // reset password - string message OK or Error Message : status OK always.
    @PutMapping("/resetpassword")
    public ResponseEntity<ResetPassword> ResetPassword(@RequestBody ResetPassword resetPassword, @RequestHeader("E20Token") String token)
    {  
        var ts = new TokenServices(); 
        var useToken = (token == null) ? "" : token;
        var valid = ts.CheckToken(useToken, adminId, secret, validate);
        if(!valid)
        {
            var message = "security: invalid token.";
            resetPassword.setMessage(message);
            return new ResponseEntity<ResetPassword>(
                new ResetPassword(true, message), null, HttpStatus.BAD_REQUEST); 
         
        }
        return customerService.ResetPassword(resetPassword);  
    }

    // reset customer
    @PutMapping("/resetcustomer")
    public ResponseEntity<ResetCustomer> ResetCustomer(@RequestBody ResetCustomer resetCustomer, @RequestHeader("E20Token") String token)
    {
        var ts = new TokenServices(); 
        var useToken = (token == null) ? "" : token;
        var valid = ts.CheckToken(useToken, adminId, secret, validate);
        if(!valid)
        {
            var message = "security: invalid token.";
            return new ResponseEntity<ResetCustomer>(
                    new ResetCustomer(true, message), null, HttpStatus.BAD_REQUEST); 
                
        }
        return customerService.ResetCustomer(resetCustomer);
      
    }   
         
}
