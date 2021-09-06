package p20.e20insurance.e20insurance.Services;   

import java.util.ArrayList;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import p20.e20insurance.e20insurance.Entities.Customer; 
import p20.e20insurance.e20insurance.Entities.PlanData;
import p20.e20insurance.e20insurance.POJO.ResetPassword;
import p20.e20insurance.e20insurance.POJO.Cust;
import p20.e20insurance.e20insurance.POJO.ResetCustomer;  
import p20.e20insurance.e20insurance.Repositories.CustomerRepository;

@Service
public class CustomerService {
  
    @Autowired
    CustomerRepository customerRepository;  

    // holds current customer.
    @Autowired
    Cust cust;

    @Autowired
    Customer customer;  

    public ResponseEntity<List<Customer>> getAllCustomers()
    {  

        try
        {  

          List<Customer> result = new ArrayList<Customer>(); 
          result = customerRepository.listCustomers();
          var status = (result == null) ?  HttpStatus.NOT_FOUND : HttpStatus.FOUND;
         if( status == HttpStatus.PERMANENT_REDIRECT || status == HttpStatus.TEMPORARY_REDIRECT)
         {
             status = HttpStatus.FOUND;
         } 
         return new ResponseEntity<List<Customer>>(result,status); 

        }
        catch(DataAccessException e)
        { 
            var status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<List<Customer>>(status); 
        } 

    }
 
    public Customer getCustomerInternal(String custId)
    {
        try 
        { 
                return customerRepository.findCustomer(custId); 
            
        }
        catch(Exception e)
        { 
             return null;
        }
    }

    public ResponseEntity<Customer> getCustomer(String custId, Boolean newToken,
     String secret, String timeout)
    {
        try 
        { 
            var customer = customerRepository.findCustomer(custId);
            var status = (customer == null) ?  HttpStatus.NOT_FOUND : HttpStatus.FOUND;
            if( status == HttpStatus.PERMANENT_REDIRECT || status == HttpStatus.TEMPORARY_REDIRECT)
            {
                status = HttpStatus.FOUND;
            }
            MultiValueMap<String, String> headers = null;
            if(newToken && status == HttpStatus.FOUND)
            {  
                final String DEFAULT_MINUTES = "14";
                var ts = new TokenServices();
                String time = (timeout == null) ? DEFAULT_MINUTES : timeout;
                Integer milliMinutes = Integer.valueOf(time) * 1000;
                var token = ts.NewToken(custId, secret, milliMinutes); 
                headers = new LinkedMultiValueMap<String,String>();  
                headers.set("E20Token",token); 
            } 
            return new ResponseEntity<Customer>(customer, headers , status);  
        } 
        catch(Exception e)
        { 
             if(e.getMessage().indexOf("did not return a unique result") > -1)
             {
                // return not found if duplicates instead of server down.
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
             }

             return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addCustomer(Customer cust, String secret, String timeout)
    {
        // Token Stragety: 

        // since this is only used for registration it always
        // returns a token in the header message 
        // note: signin returns a customer so we need to use
        // header here to be consitent; do not want to place
        // tokens in models. 

        String token = "";
        try
        { 
           
           customerRepository.save(cust);   
           final String DEFAULT_MINUTES = "14";
           var ts = new TokenServices();
           String time = (timeout == null) ? DEFAULT_MINUTES : timeout;
           Integer milliMinutes = Integer.valueOf(time) * 1000;
           String custId = cust.getcustId();
           token = ts.NewToken(custId, secret, milliMinutes); 
           MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();  
           headers.set("E20Token",token);
           return new ResponseEntity<String>("", headers, HttpStatus.OK);  
 
        }
        catch(DataAccessException e)
        {
            var message = "Add Customer Fails with message : " + e.getMessage();
            return new ResponseEntity<String>(message, null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

   

    public boolean resetCustomerKey(String currentCustomerId, String newCustomerId)
    {
        try
        {  
        
          customerRepository.resetCustomerKey(currentCustomerId, newCustomerId);
          return true;

        }
        catch(DataAccessException e)
        {
            return false;
        }
    }

    
    public void updateCustomer(Customer cust)
    {
 
         customerRepository.save(cust);
       
    }

    public void updatePlan(PlanData planData)
    {
        // check token before updating

        String custId = planData.getcustId();
        String plan = planData.getCustPlan();

        try
        {
            customerRepository.updatePlan(custId, plan);
        }
        catch(Exception e)
        { 
             
        }
      
    }  

    // administrator actions

    public ResponseEntity<ResetPassword> ResetPassword(ResetPassword resetPassword)
    {
        var currentCustId = resetPassword.getCustId(); 
        var newPassword = resetPassword.getNewPassword();  

        // edit to make sure customer exists  
        try
        { 

            var cust = getCustomerInternal(currentCustId);
            var currentCustomerMissing = (cust == null);
            if(currentCustomerMissing)
            {
                var message = "Customer for password reset, " + currentCustId + ", does not exist.";  
                return new ResponseEntity<ResetPassword>(
                       new ResetPassword(true, message), null, HttpStatus.OK); 
            }
        }
        catch(Exception e)
        { 
            var message = "Current Customer Edit Error: " + e.getMessage(); 
            return new ResponseEntity<ResetPassword>(
                       new ResetPassword(true, message), null, HttpStatus.OK); 
        }


        try
        {
            customerRepository.resetPassword(currentCustId, newPassword);
            var message = "Password reset successfully.";
            return new ResponseEntity<ResetPassword>(
                new ResetPassword(true, message), null, HttpStatus.OK); 
            
        }
        catch(Exception e)
        { 
             var message  = e.getMessage();
             return new ResponseEntity<ResetPassword>(
                new ResetPassword(true, message), null, HttpStatus.OK); 
        } 

    }

    public ResponseEntity<ResetCustomer> ResetCustomer(ResetCustomer resetCustomer)
    {
      
         // new customer edit  - should not exist.
         var newCustomerId = resetCustomer.getNewCustId(); 
         try
         {
            customer = getCustomerInternal(newCustomerId);
            var newCustomerExists = (customer != null);
            if(newCustomerExists) 
             {
                 var message = "New Customer, " + newCustomerId + ", already exists."; 
                 return new ResponseEntity<ResetCustomer>(
                        new ResetCustomer(true, message), null, HttpStatus.OK); 
             }
         }
         catch(Exception e)
         { 
             var message = "New Customer Edit Error: " + e.getMessage(); 
             return new ResponseEntity<ResetCustomer>(
                    new ResetCustomer(true, message), null, HttpStatus.OK); 
         }

        /* current customer edit - should exist - place in holding for insertion to
           new customer */ 
        var currentCustomerId = resetCustomer.getCurCustId(); 

        try
        { 
            // cust has the current customer data. 
            customer = getCustomerInternal(currentCustomerId);
            var currentCustomerMissing = (customer == null);
            if(currentCustomerMissing) 
            {
                var message = "Customer to reset, " + currentCustomerId + ", does not exist.";  
                return new ResponseEntity<ResetCustomer>(
                       new ResetCustomer(true, message), null, HttpStatus.OK); 
            }
        }
        catch(Exception e)
        { 
            var message = "Current Customer Edit Error: " + e.getMessage();  
            return new ResponseEntity<ResetCustomer>(
                   new ResetCustomer(true, message), null, HttpStatus.OK); 
        }

        // put new customer id in the current customer data making it 
        // a new customer. 
        // customer.setcustId(newCustomerId);
        
        // do the reset operation - add new customer if successful delete current customer.
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        if(resetCustomerKey(currentCustomerId,newCustomerId))
        {

            var message = "Customer Reset Operation Completed Successfully."; 
            return new ResponseEntity<ResetCustomer>(
                   new ResetCustomer(true, message), null, HttpStatus.OK);  

        } 
        else
        {
            var message =  "Unable to add new customer: " + newCustomerId + " operation failed. "; 
            return new ResponseEntity<ResetCustomer>(
                   new ResetCustomer(true, message), null, HttpStatus.OK); 
        }

    }

    protected void CopyToEntity(Cust cust, Customer customer)
    {
        // Copy POJO input data to customer entity.

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
    
}
