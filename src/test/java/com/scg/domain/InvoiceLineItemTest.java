package com.scg.domain;

import com.scg.util.Name;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/15/2017.
 */
public class InvoiceLineItemTest {
    Name contact = new Name("Singh","Robin", "");
    Consultant consultant = new Consultant(contact);
    LocalDate date = LocalDate.of(2017,03,20);
    Skill skill = Skill.SOFTWARE_ENGINEER;
    int hours = 8;
    InvoiceLineItem lineItem=new InvoiceLineItem( date, consultant, skill,hours);
    @Test
    public void getConsultant() throws Exception {
    Consultant actual = lineItem.getConsultant();
        assertEquals(consultant,actual);
    }

    @Test
    public void getSkill() throws Exception {
        Skill actual = lineItem.getSkill();
        assertEquals(skill, actual);
    }

    @Test
    public void getCharge() throws Exception {
        int actual = lineItem.getCharge();
        assertEquals(1200,actual);
    }

    @Test
    public void getHours() throws Exception {
        int actual = lineItem.getHours();
        assertEquals(8,actual);
    }

}