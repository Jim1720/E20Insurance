package p20.e20insurance.e20insurance.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import p20.e20insurance.e20insurance.Entities.Claim;
import p20.e20insurance.e20insurance.Entities.PayData;
import p20.e20insurance.e20insurance.Entities.StampData;
import p20.e20insurance.e20insurance.Repositories.ClaimRepository;

@Service
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository; 

    public ResponseEntity<String> addClaim(Claim claim)
    {
        try
        { 
          claimRepository.save(claim); 
          return new ResponseEntity<String>(HttpStatus.OK);
        }
        catch(DataAccessException e)
        {
            var message = e.getMessage();
            return new ResponseEntity<String>(message, null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    public ResponseEntity<Claim>getClaim(String ClaimIdNumber)
    {
        try
        { 
            var result = claimRepository.getClaim(ClaimIdNumber);
            var status = (result == null) ?  HttpStatus.NOT_FOUND : HttpStatus.FOUND;
            if( status == HttpStatus.PERMANENT_REDIRECT || status == HttpStatus.TEMPORARY_REDIRECT)
            {
                status = HttpStatus.FOUND;
            } 
            return new ResponseEntity<Claim>(result,status); 
        }
        catch(DataAccessException e)
        {
            var status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<Claim>(status); 
        }

    }

    public ResponseEntity<List<Claim>> getClaimHistory(String custId)
    {
        try
        { 
            // https://stackoverflow.com/questions/56499565/how-to-use-findall-on-crudrepository-returning-a-list-instead-of-iterable
       
          List<Claim> result = new ArrayList<Claim>(); 
          result = claimRepository.getHistory(custId);  
          var status = (result == null) ?  HttpStatus.NOT_FOUND : HttpStatus.FOUND;
         if( status == HttpStatus.PERMANENT_REDIRECT || status == HttpStatus.TEMPORARY_REDIRECT)
         {
             status = HttpStatus.FOUND;
         } 
         return new ResponseEntity<List<Claim>>(result,status); 

        }
        catch(DataAccessException e)
        { 
            var status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<List<Claim>>(status); 
        } 
    }

    
    public ResponseEntity<String> stampClaim(StampData stampData)
    {
        try
        { 
          var adjusting = stampData.getAdjustingClaimIdNumber();
          var adjusted = stampData.getAdjustedClaimIdNumber();
          var date = stampData.getAdjustmentDate();
          claimRepository.stampClaim(adjusting, adjusted, date);
          return new ResponseEntity<String>(HttpStatus.OK);
        }
        catch(DataAccessException e)
        {
            var message = e.getMessage();
            return new ResponseEntity<String>(message, null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    public ResponseEntity<String> payClaim(PayData payData)
    {
        try
        { 
          var ClaimIdNumber = payData.getClaimIdNumber().trim();
          var Amount = payData.getPaymentAmount(); 
          var PaymentDate = payData.getPaymentDate();
          claimRepository.payClaim(ClaimIdNumber, Amount, PaymentDate);
          return new ResponseEntity<String>(HttpStatus.OK);
        }
        catch(DataAccessException e)
        {
            var message = e.getMessage();
            return new ResponseEntity<String>(message, null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
   
    
}
