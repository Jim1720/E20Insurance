package p20.e20insurance.e20insurance.POJO; 
import java.io.Serializable; 
import org.springframework.stereotype.Component;  

@Component 
public class Cust implements Serializable  {  


    /* pojo - check function naming conventions; field names must be lower case to map to request */
    
	private static final long serialVersionUID = 1L;
 
    private int id; 
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
 
 
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    // functions capped fields not... cust.

    public String getCustId() {
        return custId;
    }

    public void setcustId(String custId) {
        this.custId = custId;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustpass(String custPassword) {
        this.custPassword = custPassword;
    }

    public String getCustFirst()
    {
        return custFirst;
    }

    public void setCustFirst(String custFirst)
    {
        this.custFirst = custFirst;
    }

    public String getCustLast()
    {
        return custLast;
    }

    public void setCustLast(String custLast)
    {
        this.custLast = custLast;
    } 
    public String getCustPlan()
    {
        return this.custPlan;
    }
    public void setCustPlan(String custplan)
    {
        this.custPlan = custplan;
    }
 
 
    public String getCustEmail()
    {
        return custEmail;
    }

    public void setCustEmail(String email)
    {
        this.custEmail = email;
    }

    public String getCustMiddle()
    {
        return custMiddle;
    }

    public void setCustMiddle(String middle)
    {
        this.custMiddle = middle;
    }

    public String getCustGender()
    {
        return custGender;
    }

    public void setCustGender(String gender)
    {
        this.custGender = gender;
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