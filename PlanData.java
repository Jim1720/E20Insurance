package p20.e20insurance.e20insurance.Entities; 
import java.io.Serializable; 
import org.springframework.stereotype.Component;  

@Component  
public class PlanData implements Serializable  {  
    
	private static final long serialVersionUID = 1L;
 
    private String custId;  
    private String custPlan;   
  
  

    public String getcustId() {
        return custId;
    }

    public void setcustId(String custId) {
        this.custId = custId;
    } 

    public String getCustPlan()
    {
        return this.custPlan;
    }
    public void setCustPlan(String custPlan)
    {
        this.custPlan = custPlan;
    }
 

}