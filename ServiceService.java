package p20.e20insurance.e20insurance.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 

import p20.e20insurance.e20insurance.Entities.Service;
import p20.e20insurance.e20insurance.Repositories.ServiceRepository; 
 
 
      
@org.springframework.stereotype.Service
public class ServiceService {
    
    
    @Autowired
    ServiceRepository serviceRepository;  

    public ResponseEntity<List<Service>> readServices()
    { 
       
        try
        { 
            
         List<Service> result = new ArrayList<Service>(); 
         result = serviceRepository.readServices();
         var status = (result == null) ?  HttpStatus.NOT_FOUND : HttpStatus.FOUND;
         if( status == HttpStatus.PERMANENT_REDIRECT || status == HttpStatus.TEMPORARY_REDIRECT)
         {
             status = HttpStatus.FOUND;
         } 
         return new ResponseEntity<List<Service>>(result,status); 

        }
        catch(DataAccessException e)
        { 
            var status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<List<Service>>(status); 
        } 
 
 
    }


}
