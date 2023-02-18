package p20.e20insurance.e20insurance.Entities;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity  
@Table(name = "\"Plan\"")
public class Plan implements Serializable {
    
    
	private static final long serialVersionUID = 2L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id; 

    private String PlanName;
    private String PlanLiteral;
    
    @Column(name = "\"Percent\"")
    private String Percent;

    public String getPlanName()
    {
        return PlanName;
    }
    public void setPlanName(String planName)
    {
        PlanName = planName;
    }
    public void setPlanLiteral(String planLiteral)
    {
        PlanLiteral = planLiteral;  
    } 

    public String getPlanLiteral()
    {
         return PlanLiteral;
    } 

    public void setPercent(String percent)
    {
        Percent = percent;
    }

    public String getPercent()
    {
        return Percent;
    }

}
