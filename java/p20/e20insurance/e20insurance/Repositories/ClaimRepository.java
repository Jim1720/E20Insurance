package p20.e20insurance.e20insurance.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import p20.e20insurance.e20insurance.Entities.Claim; 

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


@Repository  
public interface ClaimRepository extends CrudRepository<Claim, Integer>
{
    
    @Query("SELECT c FROM Claim c WHERE c.CustomerId = :custId")  
    public List<Claim> getHistory( 
             @Param("custId") String custId);
 
             
    @Query("SELECT c FROM Claim c WHERE c.ClaimIdNumber = :claimId") 
    public Claim getClaim( 
             @Param("claimId") String claimIdNumber);

     @Transactional()
     @Modifying(clearAutomatically = true)
     @Query("UPDATE Claim SET " +  
                    "AdjustingClaimId = :adjustingClaimIdNumber, " +
                    "AdjustedDate   = :adjustmentDate, " +
                    "ClaimStatus      =  'Adjusted',      " +
                    "AppAdjusting     =  'E10' " +
              "WHERE ClaimIdNumber    = :adjustedClaimIdNumber")  
          
     public int stampClaim(
              @Param("adjustingClaimIdNumber")   String adjustingClaimIdNumber, 
              @Param("adjustedClaimIdNumber")    String adjustedClaimIdNumber,
              @Param("adjustmentDate") String adjustmentDate
     );

     @Transactional()
     @Modifying(clearAutomatically = true)
     @Query("UPDATE Claim SET " +  
                    "PaymentAmount    = :PaymentAmount, " + 
                    "PaymentDate      = :PaymentDate, " +
                    "ClaimStatus      =  'Paid'      " +
              "WHERE ClaimIdNumber    = :ClaimIdNumber")  
          
     public int payClaim(
              @Param("ClaimIdNumber")    String ClaimIdNumber,
              @Param("PaymentAmount")    String PaymentAmount,
              @Param("PaymentDate")      String PaymentDate
     );

}
