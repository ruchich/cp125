package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/1/2017.
 */
public class ClientAccountTest {
    Address address = new Address("1616 Index Ct.","Redmond", StateCode.WA ,"98055");
    @Test
    public void getName()  {
        Name contact = new Name("Robin", "Singh","");

        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact, address);
        String actualName = acc.getName();
        Assert.assertTrue("XYZ Consultant".equals(actualName));
    }

    @Test
    public void getContact() {
        Name contact = new Name("Singh","Robin", "");
        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact, address);
        Name actualName = acc.getContact();
        Assert.assertEquals(contact, actualName);
    }

    @Test
    public void setContact()  {
        Name contact = new Name("Singh","Robin", "");
        Name contact2 = new Name("Taneja","Ram", "J.");
        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact, address);
        acc.setContact(contact2);

        Assert.assertTrue(contact2.equals(acc.getContact()));
    }

    @Test
    public void isBillable() {

        Name contact = new Name("Singh","Robin", "");
        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact,address);
        boolean actual = acc.isBillable();
        Assert.assertTrue(actual);

    }

}