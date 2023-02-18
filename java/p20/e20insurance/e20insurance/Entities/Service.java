package p20.e20insurance.e20insurance.Entities;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity  
@Table(name = "Service")
public class Service implements Serializable { 
    
	private static final long serialVersionUID = 4L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id; 

    private String ServiceName;
    private String ClaimType;
    private String ClaimTypeLiteral;
    private String Cost;

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    // no setters use is read only

    public String getServiceName()
    {
        return this.ServiceName;
    }

    public String getClaimType()
    {
        return this.ClaimType;
    }

    public String getClaimTypeLiteral()
    {
        return this.ClaimTypeLiteral;
    }
    
    public String getCost()
    {
        return this.Cost;
    }

}
