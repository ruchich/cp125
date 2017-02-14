package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;
import org.junit.Test;

import java.time.LocalDate;

import static com.scg.domain.Skill.SOFTWARE_ENGINEER;
import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/1/2017.
 */
public class ConsultantTimeTest {
    int hours = 8;
    Name name = new Name("Robin", "Singh","");
    Consultant consultant = new Consultant(name);
    LocalDate date = LocalDate.of(2017,01,20);
    Address address = new Address("1616 Index Ct.","Redmond", StateCode.WA ,"98055");
    ClientAccount cAccount0 = new ClientAccount("XYZ Consultant", name, address);
    ConsultantTime cTime = new ConsultantTime (date,cAccount0, SOFTWARE_ENGINEER, hours);
    @Test
    public void getDate()  {

        assertEquals(date, cTime.getDate());
    }

    @Test
    public void setDate()  {
        cTime.setDate(LocalDate.of(2016,05,15));
        LocalDate date = LocalDate.of(2016,05,15);
        assertEquals(date, cTime.getDate());
    }

    @Test
    public void getAccount()  {
        assertEquals(cAccount0, cTime.getAccount());
    }

    @Test
    public void setAccount()  {
        ClientAccount cAccount1= new ClientAccount("AAA Consultant", name, address);
        cTime.setAccount(cAccount1);
        assertEquals(cAccount1, cTime.getAccount());
    }

    @Test
    public void isBillable()  {
        assertTrue(cTime.isBillable());
    }

    @Test
    public void getHours()  {
        assertEquals(8, cTime.getHours());
    }

    @Test
    public void setHours()  {
        int hrs =12;
        cTime.setHours(hrs);
        assertEquals(12, cTime.getHours());
           }

    @Test
    public void getSkill() {
        assertEquals(SOFTWARE_ENGINEER, cTime.getSkill());
    }

}