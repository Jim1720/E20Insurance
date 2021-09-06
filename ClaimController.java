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

import p20.e20insurance.e20insurance.Entities.Claim;
import p20.e20insurance.e20insurance.Entities.PayData;
import p20.e20insurance.e20insurance.Entities.StampData;
import p20.e20insurance.e20insurance.POJO.ClaimData;
import p20.e20insurance.e20insurance.Services.ClaimService;
import p20.e20insurance.e20insurance.Services.TokenServices;

@RestController

public class ClaimController {  
 
      
    @Value("${E20Secret:notSet}")
    String secret;   

    @Value("${E20Validate:on}")
    String validate;   

    @Autowired
    ClaimService claimService;  

    @Autowired
    Claim claim;  // Entity

    @Autowired
    ClaimData claimData; // Rest transport DTO object
 

    // claim create 201
    @PostMapping("/claim")
    public ResponseEntity<String> AddClaim(@RequestBody ClaimData claimData, @RequestHeader("E20Token") String token)
    { 
      
      var ts = new TokenServices();
      var custId = claimData.getCustomerId().trim(); 
      var useToken = (token == null) ? "" : token;
      var valid = ts.CheckToken(useToken, custId, secret, validate);
      if(!valid)
      { 

          return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
              
      }
      CopyToEntity(claimData, claim);
      claimService.addClaim(claim); 
      return new ResponseEntity<String>(HttpStatus.OK);

    }

     // claim lookup 
     @GetMapping("/claim/{claimId}")
     public ResponseEntity<Claim> getClaim(@PathVariable String  claimId)
     { 
        return  claimService.getClaim(claimId);
     }

     // claim history lookup 
     @GetMapping("/history/{custId}")
     public ResponseEntity<List<Claim>> getClaimHistory(@PathVariable String  custId)
     { 
        return  claimService.getClaimHistory(custId);
     }

    // claim stamp - stamp adjusted claim 
    @PutMapping("/stampClaim/{claimId}")
    public ResponseEntity<String> stampClaim(@RequestBody StampData stampData, @RequestHeader("E20Token") String token)
    { 
        var ts = new TokenServices();
        var custId = stampData.getCustId();
        var useToken = (token == null) ? "" : token;
        var valid = ts.CheckToken(useToken, custId, secret, validate);
        if(!valid)
        { 
              return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return  claimService.stampClaim(stampData);
    }

      // claim payment
      @PutMapping("/payClaim")
      public ResponseEntity<String> payClaim(@RequestBody PayData payData, @RequestHeader("E20Token") String token)
      { 
        var ts = new TokenServices();
        var custId = payData.getCustId().trim(); 
        var useToken = (token == null) ? "" : token;
        var valid = ts.CheckToken(useToken, custId, secret, validate);
        if(!valid)
        {
               return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
         return  claimService.payClaim(payData);
      }

      // future: 
      // o pay claim 

    private void CopyToEntity(ClaimData claimData, Claim claim)
    {
         claim.setCustomerId(claimData.getCustomerId());
         claim.setPatientFirst(claimData.getPatientFirst());
         claim.setPatientLast(claimData.getPatientLast());
         claim.setClaimIdNumber(claimData.getClaimIdNumber());
         claim.setClaimDescription(claimData.getClaimDescription());
         claim.setProcedure1(claimData.getProcedure1());
         claim.setProcedure2("");
         claim.setDiagnosis1(claimData.getDiagnosis1());
         claim.setDiagnosis2("");
         claim.setClaimType(claimData.getClaimType());
         claim.setPhysician(claimData.getPhysician());
         claim.setClinic(claimData.getClinic()); 
         claim.setDateService(claimData.getDateService());
         claim.setService(claimData.getService());
         claim.setLocation(claimData.getLocation());
         claim.setTotalCharge(claimData.getTotalCharge());
         claim.setCoveredAmount(claimData.getCoveredAmount());
         claim.setBalanceOwed(claimData.getBalanceOwed());
         claim.setPaymentDate(claimData.getPaymentDate());
         claim.setPaymentAmount(claimData.getPaymentAmount());
         claim.setDateAdded(claimData.getDateAdded());  
         claim.setAdjustedClaimId(claimData.getAdjustedClaimId());
         claim.setAdjustingClaimId(claimData.getAdjustingClaimId());
         claim.setAdjustedDate(claimData.getAdjustedDate());
         claim.setClaimStatus(claimData.getClaimStatus());
         claim.setAppAdjusting(claimData.getAppAjusting());
         claim.setReferral(claimData.getReferral());
         claim.setPaymentAction(claimData.getPaymentActon());
         // claim type set above.
         claim.setDateConfine(claimData.getDateConfine());
         claim.setDateRelease(claimData.getDateRelease());
         claim.setToothNumber(claimData.getToothNumber());
         claim.setEyeware(claimData.getEyeware());
         claim.setDrugName(claimData.getDrugName());
         claim.setPlanId(claimData.getPlanId()); 


    }
    
}
