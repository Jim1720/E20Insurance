package p20.e20insurance.e20insurance.Repositories;  
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import p20.e20insurance.e20insurance.Entities.Customer; 

@Repository 
public interface CustomerRepository extends CrudRepository<Customer, Integer>
{
 
     @Query("SELECT cust FROM Customer cust WHERE cust.custId = :custId") 
     Customer findCustomer( 
              @Param("custId") String custId);  

     @Transactional()
     @Modifying(clearAutomatically = true)
     @Query("UPDATE Customer  SET custPlan = :custPlan WHERE custId = :custId")
     public int updatePlan(
              @Param("custId")   String custId,
              @Param("custPlan") String custPlan  
     );

     // reset password
     @Transactional()
     @Modifying(clearAutomatically = true)
     @Query("UPDATE Customer SET custPassword = :newPassword WHERE custId = :custId")
     public void resetPassword(
            @Param("custId") String custId, 
            @Param("newPassword") String newPassword

     );

     // delete query to remove customer
     @Query("DELETE FROM Customer WHERE custId = :custId")
     public void deleteCustomer(
          @Param("custId") String custId
     );

     // list customers
     @Query("SELECT c FROM Customer c")  
     public List<Customer> listCustomers();  

      // reset customer key pass custId and the newValue.
      @Transactional()
      @Modifying(clearAutomatically = true)
      @Query("UPDATE Customer SET custId = :newValue WHERE custId = :custId")
      public void resetCustomerKey(
             @Param("custId") String custId, 
             @Param("newValue") String newValue
      );

}