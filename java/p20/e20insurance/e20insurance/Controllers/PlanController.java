package p20.e20insurance.e20insurance.Controllers;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import p20.e20insurance.e20insurance.Entities.Plan;
import p20.e20insurance.e20insurance.Services.PlanService;

@RestController 
public class PlanController {

    
    @Autowired
    PlanService planService;  

    @GetMapping("/readPlans")
    public ResponseEntity<List<Plan>> readPlans()
    { 
        
       return  planService.readPlans();

    }

}
