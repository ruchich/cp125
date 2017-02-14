package com.scg.domain;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public class InvoiceLineItem {
	java.time.LocalDate date;
    Consultant consultant;
    Skill skill;
    int hours;
	private double charge;
	
	public InvoiceLineItem(java.time.LocalDate date,
            Consultant consultant,
            Skill skill,
            int hours){
		if(hours<=0){throw new IllegalArgumentException("Invoiceline item requires hours>0");
		
		}
		this.date = date;
		this.consultant = consultant;
		this.skill = skill;
		this.hours = hours;
		//this.charge = this.skill*this.hours;
	}
	
/**	Get the consultant for this line item.

	* @ return 	The consultant.
	* */
	public Consultant getConsultant(){
	return consultant;	
	}

/**	Get the skill for this line item.

	* @return The skill.
	*/
	public Skill getSkill(){

		return skill;

}

	/**
	 * @return the charge
	 */
	public int getCharge() {
		int charge = hours*getSkill().getRate();
		return charge;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}
	
}
