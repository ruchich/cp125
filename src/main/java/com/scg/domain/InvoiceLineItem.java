package com.scg.domain;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public class InvoiceLineItem {
	java.time.LocalDate date;
    Consultant consultant;
    Skill skill;
    int hours;
	
	public InvoiceLineItem(java.time.LocalDate date,
            Consultant consultant,
            Skill skill,
            int hours){
		this.date = date;
		this.consultant = consultant;
		this.skill = skill;
		this.hours = hours;
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
