package p20.e20insurance.e20insurance.Services; 

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
  
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm; 

/* logging */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class TokenServices { 
   
    public  String NewToken(String someId, String secret, Integer milliMinutes)
    {
        // called by signin, adminsignin and register to create a 
        // json web token.

        // a. check for secret 
        // b. check expiration time 30 minutes. 

        // store in sessionBean for later comparison in verification routine

        String token = "";
        try
        {  
             
            // need to use claims map due to setIssuedAt. 
            Map<String, Object> claims = new HashMap<>(); 

            claims.put("iss","E20Insurance");
            claims.put("sub",someId);
            claims.put("aud","E10Insurance");  

            // convert seconds to minutes
            milliMinutes = milliMinutes * 60;
 
            token = Jwts.builder()
                    .setIssuer("E20Insurance")
                    .setAudience("E10Insurance")
                    .setClaims(claims) 
                    .setIssuedAt(new Date(System.currentTimeMillis())) 
                    .setExpiration(new Date(System.currentTimeMillis() + milliMinutes)) 
                    .signWith(SignatureAlgorithm.HS512, secret).compact();   
 
            return token; 

        } 
        catch(Exception e)
        {
              token ="notSet";
        }
        
        return token;
       
    }

    public Boolean CheckToken(String token, String custId, String secret, String validate)
    {

        // match secret and check expiration. 

        // bypass switch 
        // Validate = "on" will check
        // Validate = "off" will skip

         //var notSet = "notSet"; // change this line for debug purposes.
         // if(token.equals(notSet))
         //{
         //    return true;
         //  }

         
         if(validate.toUpperCase().equals("OFF")) 
         {
             return true; // skip check 
         }  

         final Logger LOGGER = LoggerFactory.getLogger(TokenServices.class);

         

         Claims claims;

         try 
         {   
              boolean isSigned_not_tampered = Jwts.parser().isSigned(token);
              if(!isSigned_not_tampered)
              {
                 return false;
              }

                try
                { 

                    claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(); 

                }
                catch(ExpiredJwtException expired)
                {
                    
                    var message = "Expired: token has expired.";
                    LOGGER.info(message);
                    return false;
                }  


              var message = "";
              // here the 'claims' refers to jwt not the app claims!!
              var claimsCustId = claims.get("sub");
              if(claimsCustId.equals(custId))
              { 
                   message = "Token verified.";
                   LOGGER.info(message);

              }
              else
              {
                  message = "Warning: token unverified.";
                  LOGGER.info(message);
              }


              return true;  /* debugging */
         } 
         catch(Exception e)
         {
            return false;
         } 
    } 
}
