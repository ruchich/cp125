package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static com.scg.domain.NonBillableAccount.VACATION;
import static com.scg.domain.Skill.SOFTWARE_ENGINEER;
import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/1/2017.
 */
public class TimeCardTest {
    Address address = new Address("1616 Index Ct.","Redmond", StateCode.WA ,"98055");
    @Test
    public void getConsultant()  {
        Name name = new Name( "Smith","Carl","J.");
        Consultant consultantName = new Consultant(name );
        String cName= consultantName.getName().toString();
        Assert.assertEquals(name.toString(), cName);
    }

    @Test
    public void getTotalBillableHours() throws Exception {

        int hours = 8;
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
        ClientAccount cAccount = new ClientAccount("ABC Consultant", name,address);
        ConsultantTime cTime = new ConsultantTime (date, cAccount,Skill.SOFTWARE_ENGINEER, hours);
        TimeCard tC = new TimeCard(consultant,date);
        List<ConsultantTime> consultantTimes = new ArrayList<>();
        tC.addConsultantTime(cTime);
        int totalBillableHours = tC.getTotalBillableHours();
        Assert.assertTrue(totalBillableHours==8);
    }

    @Test
    public void getTotalNonBillableHours() throws Exception {

        int hours = 8;
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
         ConsultantTime cTime = new ConsultantTime (date, NonBillableAccount.VACATION,Skill.SOFTWARE_ENGINEER, hours);
        TimeCard tC = new TimeCard(consultant,date);
        List<ConsultantTime> consultantTimes = new ArrayList<>();
        tC.addConsultantTime(cTime);
        int totalnonBillableHours = tC.getTotalNonBillableHours();
        Assert.assertTrue(totalnonBillableHours==8);
    }

    @Test
    public void getConsultingHours() throws Exception {
        int hours = 8;
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
        ConsultantTime cTime = new ConsultantTime (date, NonBillableAccount.VACATION,Skill.SOFTWARE_ENGINEER, hours);
        ClientAccount cAccount = new ClientAccount("ABC Consultant", name, address);
        ConsultantTime cTime1 = new ConsultantTime (date, cAccount,Skill.SOFTWARE_ENGINEER, hours);
        TimeCard tC = new TimeCard(consultant,date);
        tC.addConsultantTime(cTime);
        tC.addConsultantTime(cTime1);
        List<ConsultantTime> expectedConsultantTimes = new ArrayList<>();
        expectedConsultantTimes.add(cTime);
        expectedConsultantTimes.add(cTime1);
        List<ConsultantTime> actualconsultantTimes = tC.getConsultingHours();
        Assert.assertTrue(expectedConsultantTimes.equals(actualconsultantTimes));
    }

    @Test
    public void addConsultantTime() throws Exception {

        int hours = 8;
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
        ConsultantTime cTime = new ConsultantTime (date, NonBillableAccount.VACATION,Skill.SOFTWARE_ENGINEER, hours);
        ClientAccount cAccount = new ClientAccount("ABC Consultant", name, address);
        ConsultantTime cTime1 = new ConsultantTime (date, cAccount,Skill.SOFTWARE_ENGINEER, hours);
        TimeCard tC = new TimeCard(consultant,date);
        tC.addConsultantTime(cTime);
        tC.addConsultantTime(cTime1);
        List<ConsultantTime> expectedConsultantTimes = new ArrayList<>();
        expectedConsultantTimes.add(cTime);
        expectedConsultantTimes.add(cTime1);
        List<ConsultantTime> actualconsultantTimes = tC.getConsultingHours();
        Assert.assertTrue(expectedConsultantTimes.equals(actualconsultantTimes));

    }

    @Test
    public void getTotalHours() throws Exception {

        int hours = 8;
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
        ConsultantTime cTime = new ConsultantTime (date, NonBillableAccount.VACATION,Skill.SOFTWARE_ENGINEER, hours);
        ClientAccount cAccount = new ClientAccount("ABC Consultant", name, address);
        ConsultantTime cTime1 = new ConsultantTime (date, cAccount,Skill.SOFTWARE_ENGINEER, hours);
        TimeCard tC = new TimeCard(consultant,date);
        tC.addConsultantTime(cTime);
        tC.addConsultantTime(cTime1);
        int totalnonBillableHours = tC.getTotalNonBillableHours();
        int totalBillableHours = tC.getTotalBillableHours();
        int totalHrs = totalnonBillableHours + totalBillableHours;
        Assert.assertTrue(totalHrs==16);
    }

    @Test
    public void getWeekStartingDay()  {
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
        TimeCard tC = new TimeCard(consultant,date);
        LocalDate actualWeekStartingDay = tC.getWeekStartingDay();
        Assert.assertEquals(date,actualWeekStartingDay);
    }

    @Test
    public void getBillableHoursForClient() throws Exception {
        int hours = 8;
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
        ClientAccount cAccount0 = new ClientAccount("XYZ Consultant", name,address);
        ConsultantTime cTime = new ConsultantTime (date,cAccount0,Skill.SOFTWARE_ENGINEER, hours);
        ClientAccount cAccount = new ClientAccount("ABC Consultant", name,address);
        ConsultantTime cTime1 = new ConsultantTime (date, cAccount,Skill.SOFTWARE_ENGINEER, hours);
        TimeCard tC = new TimeCard(consultant,date);
        tC.addConsultantTime(cTime);
        tC.addConsultantTime(cTime1);
        List<ConsultantTime> actualBillableHoursForClient = tC.getBillableHoursForClient("ABC Consultant");
        List<ConsultantTime> expectedBillableHoursForClient = new ArrayList<>();
        expectedBillableHoursForClient.add(cTime1);
        Assert.assertTrue(expectedBillableHoursForClient.equals(actualBillableHoursForClient));


    }



    @Test
    public void toReportString(){
        int hours = 8;
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        LocalDate date = LocalDate.of(2017,01,20);
        ClientAccount cAccount0 = new ClientAccount("XYZ Consultant", name, address);
        ConsultantTime cTime = new ConsultantTime (date,cAccount0,Skill.SOFTWARE_ENGINEER, hours);
        ClientAccount cAccount = new ClientAccount("ABC Consultant", name, address);
        ConsultantTime cTime1 = new ConsultantTime (date, cAccount,Skill.SOFTWARE_ENGINEER, hours);
        TimeCard tC = new TimeCard(consultant,date);
        tC.addConsultantTime(cTime);
        tC.addConsultantTime(cTime1);
       String actual = tC.toReportString().trim();
        String expected = String.format("====================================================================%nConsultant: Robin , Singh \t\t Week Starting: Jan 20, 2017%nBillable Time:%nAccount\t\t\tDate\t\tHours\tSkill%n----------------------  --------------  -----   --------------------%nXYZ Consultant\t01/20/2017\t8\tSOFTWARE_ENGINEER%nABC Consultant\t01/20/2017\t8\tSOFTWARE_ENGINEER%n%nNon-Billable Time:%nAccount\t\t\tDate\t\tHours\tSkill%n----------------------  --------------  -----   --------------------%nSummary:%nTotal Billable Hours:\t\t\t 16 %nTotal Non-Billable Hours: \t\t 0 %nTotal Hours:  \t\t\t\t16 %n%n====================================================================");
        //String expected = String.format("%n====================================================================%nConsultant: Robin , Singh\t\t Week Starting: Jan 20, 2017%nBillable Time:%nAccount\t\t\tDate\t\tHours\tSkill%n----------------------  --------------  -----   --------------------%nXYZ Consultant\t01/20/2017\t8\tSOFTWARE_ENGINEER%nABC Consultant\t01/20/2017\t8\tSOFTWARE_ENGINEER%n%nNon-Billable Time:%nAccount\t\t\tDate\t\tHours\tSkill%n----------------------  --------------  -----   --------------------%nSummary:%nTotal Billable Hours:\t\t\t 16 %nTotal Non-Billable Hours: \t\t 0 %nTotal Hours:  \t\t\t\t16 %n%n====================================================================%n");
        System.out.println(expected);
        String expectedt = expected.trim();
       assertEquals(expected,actual);

    }

}