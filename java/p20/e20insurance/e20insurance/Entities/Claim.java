package p20.e20insurance.e20insurance.Entities; 
import java.io.Serializable;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; 
import org.springframework.stereotype.Component;   

@Component
@Entity  
@Table(name = "Claim")

public class Claim  implements Serializable  {  
    
	private static final long serialVersionUID = 2L; 

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)  
    //private int id; 
    
    @Id
    private String ClaimIdNumber; 

    private String ClaimDescription;
    private String CustomerId;
    private String PlanId;
    private String PatientFirst;
    private String PatientLast;
    private String Diagnosis1;
    private String Diagnosis2;
    private String Procedure1;
    private String Procedure2;
    private String Procedure3;
    private String Physician;
    private String Clinic;
    private String DateService;   
    private String Service;
    private String Location;
    private String TotalCharge;
    private String CoveredAmount;
    private String BalanceOwed;
    private String PaymentAmount;
    private String PaymentDate;
    private String DateAdded;
    private String AdjustedClaimId;
    private String AdjustingClaimId;
    private String AdjustedDate;
    private String AppAdjusting;
    private String ClaimStatus;
    private String Referral;
    private String PaymentAction;
    private String ClaimType;
    private String DateConfine;
    private String DateRelease;
    private String ToothNumber;
    private String DrugName;
    private String Eyeware; 

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/
 
  
    public String getClaimIdNumber()
    {
        return ClaimIdNumber;
    }

    public void setClaimIdNumber(String ClaimIdNumber)
    {
        this.ClaimIdNumber = ClaimIdNumber;
    }

    public String getClaimDescription()
    {
        return ClaimDescription;
    }

    public void setClaimDescription(String ClaimDescription)
    {
        this.ClaimDescription = ClaimDescription;
    }
    public String getCustomerId()
    {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId)
    {
        this.CustomerId = CustomerId;
    }
 
    public String getPlanId()
    {
        return PlanId;
    }

    public void setPlanId(String PlanId)
    {
        this.PlanId = PlanId;
    }

    public String getPatientFirst()
    {
        return PatientFirst;
    }

    public void setPatientFirst(String PatientFirst)
    {
        this.PatientFirst = PatientFirst;
    }

    public String getPatientLast()
    {
        return PatientLast;
    }

    public void setPatientLast(String PatientLast)
    {
        this.PatientLast = PatientLast;
    }

    public String getDiagnosis1()
    {
        return Diagnosis1;
    }

    public void setDiagnosis1(String Diagnosis1)
    {
        this.Diagnosis1 = Diagnosis1;
    }

    public String getDiagnosis2()
    {
        return Diagnosis2;
    }

    public void setDiagnosis2(String Diagnosis2)
    {
        this.Diagnosis2 = Diagnosis2;
    }

    public String getProcedure1()
    {
        return Procedure1;
    }

    public void setProcedure1(String Procedure1)
    {
        this.Procedure1 = Procedure1;
    }

    public String getProcedure2()
    {
        return Procedure2;
    }

    public void setProcedure2(String Procedure2)
    {
        this.Procedure2 = Procedure2;
    }

    public String getProcedure3()
    {
        return Procedure3;
    }

    public void setProcedure3(String Procedure3)
    {
        this.Procedure3 = Procedure3;
    }

    public String getPhysician()
    {
        return this.Physician;
    }

    public void setPhysician(String Physician)
    {
        this.Physician = Physician;
    }

    public String getClinic()
    {
        return this.Clinic;
    }

    public void setClinic(String Clinic)
    {
        this.Clinic = Clinic;
    }

    public String getDateService()
    {
        return this.DateService;
    }

    public void setDateService(String DateService)
    {
        this.DateService = DateService;
    }

    
    public String getService()
    {
        return this.Service;
    }

    public void setService(String Service)
    {
        this.Service = Service;
    }

    public String getLocation()
    {
        return this.Location;
    }

    public void setLocation(String Location)
    {
        this.Location = Location;
    }

    public String getTotalCharge()
    {
        return this.TotalCharge;
    }

    public void setTotalCharge(String TotalCharge)
    {
        this.TotalCharge = TotalCharge;
    }

    public String getCoveredAmount()
    {
        return this.CoveredAmount;
    }

    public void setCoveredAmount(String CoveredAmount)
    {
        this.CoveredAmount = CoveredAmount;
    }

    public String getBalanceOwed()
    {
        return this.BalanceOwed;
    }

    public void setBalanceOwed(String BalanceOwed)
    {
        this.BalanceOwed = BalanceOwed;
    }

    public String getPaymentAmount()
    {
        return this.PaymentAmount;
    }

    public void setPaymentAmount(String PaymentAmount)
    {
        this.PaymentAmount = PaymentAmount;
    }

    public String getPaymentDate()
    {
        return this.PaymentDate;
    }

    public void setPaymentDate(String PaymentDate)
    {
        this.PaymentDate = PaymentDate;
    }

    public String getDateAdded()
    {
        return this.DateAdded;
    }

    public void setDateAdded(String DateAdded)
    {
        this.DateAdded = DateAdded;
    }

    public String getAdjustedClaimId()
    {
        return this.AdjustedClaimId;
    }

    public void setAdjustedClaimId(String AdjustedClaimId)
    {
        this.AdjustedClaimId = AdjustedClaimId;
    }

    public String getAdjustingClaimId()
    {
        return this.AdjustingClaimId;
    }

    public void setAdjustingClaimId(String AdjustingClaimId)
    {
        this.AdjustingClaimId = AdjustingClaimId;
    }

    public String getAdjustedDate()
    {
        return this.AdjustedDate;
    }

    public void setAdjustedDate(String AdjustedDate)
    {
        this.AdjustedDate = AdjustedDate;
    }

    public String getAppAjusting()
    {
        return this.AppAdjusting;
    }

    public void setAppAdjusting(String AppAdjusting)
    {
        this.AppAdjusting = AppAdjusting;
    }

    public String getClaimStatus()
    {
        return this.ClaimStatus;
    }

    public void setClaimStatus(String ClaimStatus)
    {
        this.ClaimStatus = ClaimStatus;
    }

    public String getReferral()
    {
        return this.Referral;
    }

    public void setReferral(String Referral)
    {
        this.Referral= Referral;
    }

    public String getPaymentActon()
    {
        return this.PaymentAction;
    }

    public void setPaymentAction(String PaymentAction)
    { 
        this.PaymentAction = PaymentAction;
    }

    public String getClaimType()
    {
        return this.ClaimType;
    }

    public void setClaimType(String ClaimType)
    { 
        this.ClaimType = ClaimType;
    }

    public String getDateConfine()
    {
        return this.DateConfine;
    }

    public void setDateConfine(String DateConfine)
    { 
        this.DateConfine = DateConfine;
    }

    public String getDateRelease()
    {
        return this.DateRelease;
    }

    public void setDateRelease(String DateRelease)
    { 
        this.DateRelease = DateRelease;
    }

    public String getToothNumber()
    {
        return this.ToothNumber;
    }

    public void setToothNumber(String ToothNumber)
    { 
        this.ToothNumber = ToothNumber;
    }

    public String getDrugName()
    {
        return this.DrugName;
    }

    public void setDrugName(String DrugName)
    { 
        this.DrugName = DrugName;
    }

    public String getEyeware()
    {
        return this.Eyeware;
    }

    public void setEyeware(String Eyeware)
    { 
        this.Eyeware = Eyeware;
    }
}
