package p20.e20insurance.e20insurance.Entities;   
 
import java.io.Serializable;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
 
import org.springframework.stereotype.Component;  

@Component
@Entity  
@Table(name = "Customer")
//               uniqueConstraints={@UniqueConstraint(columnNames={"custId"})})
public class Customer implements Serializable  {  
    
	private static final long serialVersionUID = 1L;

   // @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)  
   // private int id; 

    @Id
    private String custId;  
    private String custPassword;   
    private String custFirst;
    private String custLast;  
    private String custPlan;
    private String custMiddle;
    private String custGender;
    private String custEmail; 
    private String custBirthDate;
    private String custPhone;
    private String custAddr1;
    private String custAddr2;
    private String custCity;
    private String custState;
    private String custZip; 
    private String appId;
    private String claimCount;    
    

    public String getcustId() {
        return custId;
    }

    public void setcustId(String custId) {
        this.custId = custId;
    }

    public String getcustPassword() {
        return custPassword;
    }

    public void setcustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    public String getcustFirst()
    {
        return custFirst;
    }

    public void setcustFirst(String custFirst)
    {
        this.custFirst = custFirst;
    }

    public String getcustLast()
    {
        return custLast;
    }

    public void setcustLast(String custLast)
    {
        this.custLast = custLast;
    } 
    public String getCustPlan()
    {
        return this.custPlan;
    }
    public void setCustPlan(String custPlan)
    {
        this.custPlan = custPlan;
    }
  

    public String getCustEmail()
    {
        return custEmail;
    }

    public void setCustEmail(String email)
    {
        this.custEmail = email;
    }

    public void setCustMiddle(String middle)
    {
        custMiddle = middle;
    }

    public String getCustMiddle()
    {
        return custMiddle;
    }

    public void setCustGender(String gender)
    {
        custGender = gender;
    }

    public String getCustGender()
    {
        return custGender;
    }

    public void setCustBirthDate(String birth)
    {
        this.custBirthDate = birth;
    }

    public String getCustBirthDate()
    {
        return custBirthDate;
    }

    public void setCustPhone(String phone)
    {
        this.custPhone = phone;
    }

    public String getCustPhone()
    {
        return custPhone;
    }
  
    public void setCustAddr1(String addr1)
    {
        this.custAddr1 = addr1;
    }

    public String getCustAddr1()
    {
        return custAddr1;
    }

    public void setCustAddr2(String addr2)
    {
        this.custAddr2 = addr2;
    }

    public String getCustAddr2()
    {
        return custAddr2;
    }

    public void setCustCity(String city)
    {
        this.custCity = city;
    }

    public String getCustCity()
    {
        return custCity;
    }

    public void setCustState(String state)
    {
        this.custState = state;
    }

    public String getCustState()
    {
        return custState;
    }

    public void setCustZip(String zip)
    {
        this.custZip = zip;
    }

    public String getCustZip()
    {
        return custZip;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getAppId()
    {
        return this.appId;
    }

    public void setClaimCount(String ClaimCount)
    {
        this.claimCount = ClaimCount;
    }

    public String getClaimCount()
    {
        return claimCount;
    }

}