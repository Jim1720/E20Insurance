package p20.e20insurance.e20insurance.Repositories;
 
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import p20.e20insurance.e20insurance.Entities.Service;
 
 

@Repository  
public interface ServiceRepository extends CrudRepository<Service, Integer>
{
     
    @Query("Select s from Service s")
    public  List<Service> readServices();  

}
