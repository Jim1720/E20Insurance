package p20.e20insurance.e20insurance.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import p20.e20insurance.e20insurance.Entities.Plan;

@Repository  
public interface PlanRepository extends CrudRepository<Plan, Integer>
{
    @Query("SELECT p FROM Plan p")  
    public List<Plan> readPlans();  

}