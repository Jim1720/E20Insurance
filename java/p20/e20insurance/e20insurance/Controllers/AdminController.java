package p20.e20insurance.e20insurance.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RestController;  
import p20.e20insurance.e20insurance.Services.TokenServices;

@RestController
public class AdminController {  

    @Value("${E20MinutesTimeout:14}")
    String timeout;  

    @Value("${E20Validate:on}")
    String validate;   

    @Value("${E20AdminIdent:notSet}")
    String ValidIdent;

    @Value("${E20AdminPass:notSet}")
    String ValidPass;    
    
    @Value("${E20Secret:notSet}")
    String secret;    

    
    @GetMapping("/adminsignin") 
    public ResponseEntity<String> AdminSignIn(@RequestHeader("E20Id") String E20Id,
                                              @RequestHeader("E20Pass") String E20Pass)
    {  
        var empty = ""; 
        var matched = false;   
        var inputAdminId = E20Id;
        var inputPassword = E20Pass;
        var missing = inputAdminId == null || inputAdminId.equals(empty) ||
                      inputPassword == null || inputPassword.equals(empty); 

        if(missing)
        {  
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND); 
        }
 
        matched = inputAdminId.equals(ValidIdent) && inputPassword.equals(ValidPass);
        if(matched)
        {
            var ts = new TokenServices();
            // the admin id stays in the sever so we pass something else back!
            final String DEFAULT_MINUTES = "14"; 
            String time = (timeout == null) ? DEFAULT_MINUTES : timeout;
            final int TO_MINUTES_CONVERT = 1000 * 60;
            Integer milliMinutes = Integer.valueOf(time) * TO_MINUTES_CONVERT;  
            var token = ts.NewToken(inputAdminId, secret, milliMinutes);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();  
            headers.set("E20Token",token); 
            return new ResponseEntity<String>("OK",headers, HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND); 
    } 
    
}
