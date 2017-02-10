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
        for (Skill s : Skill.values()) {
            if (s.equals(PROJECT_MANAGER)) {
                rate = 250;
            }
            if (s.equals(SOFTWARE_ENGINEER)) {
                rate = 200;
            }
            if (s.equals(SOFTWARE_TESTER)) {
                rate = 150;
            }
            if (s.equals(SYSTEM_ARCHITECT)) {
                rate = 100;
            }
        }
            return rate;
        }
    }




