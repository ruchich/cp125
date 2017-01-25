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


 /*   public static Skill[] values() {
        Skill[] skillType;
      //  Skill[] skillType = Skill.valueOf();
        for (Skill c : Skill.values())

            return skillType;
        }*/

        public static Skill valueOf(String name)throws IllegalArgumentException, NullPointerException {
        if (name.equals(null)){
            throw new NullPointerException();
            }
           if(Skill.valueOf()
                }
            }
            throw new IllegalArgumentException();

    }
    public String toString() {
        String str = "";
        return str;
    }
    }

