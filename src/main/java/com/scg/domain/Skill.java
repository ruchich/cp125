package com.scg.domain;


/**
 * Created by chq-ruchic on 1/20/2017.
 */
public enum Skill
{

    PROJECT_MANAGER,
    SOFTWARE_ENGINEER,
    SOFTWARE_TESTER,
    SYSTEM_ARCHITECT,
    UNKNOWN_SKILL;
	
	
    int rate;
    public int getRate() {
        	switch (this.name())
        	{
	        	case "PROJECT_MANAGER": rate = 250;
	        			break;
	        	case "SOFTWARE_ENGINEER": rate = 150;
	        			break;
	        	case "SOFTWARE_TESTER": rate = 100;
	        			break;
	        	case "SYSTEM_ARCHITECT": rate = 200;
	        		break;
	        	default: rate = 0;
	        		break;
        	}
            return rate;
        }
    }




