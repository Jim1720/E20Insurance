package p20.e20insurance.e20insurance.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import p20.e20insurance.e20insurance.Entities.Plan;
import p20.e20insurance.e20insurance.Repositories.PlanRepository;


@Service
public class PlanService {

    @Autowired
    PlanRepository planRepository;  

    public ResponseEntity<List<Plan>> readPlans()
    { 

        try
        { 
            
         List<Plan> result = new ArrayList<Plan>(); 
         result = planRepository.readPlans();
         var status = (result == null) ?  HttpStatus.NOT_FOUND : HttpStatus.FOUND;
         if( status == HttpStatus.PERMANENT_REDIRECT || status == HttpStatus.TEMPORARY_REDIRECT)
         {
             status = HttpStatus.FOUND;
         } 
         return new ResponseEntity<List<Plan>>(result,status); 

        }
        catch(DataAccessException e)
        { 
            var status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<List<Plan>>(status); 
        } 
 

    }
    
}
