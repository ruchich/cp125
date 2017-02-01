package com.scg.domain;

import com.scg.util.Name;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/1/2017.
 */
public class ClientAccountTest {
    @Test
    public void getName() throws Exception {
        Name contact = new Name("Robin", "Singh","");

        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact);
        String actualName = acc.getName();
        Assert.assertTrue("XYZ Consultant".equals(actualName));
    }

    @Test
    public void getContact() throws Exception {
        Name contact = new Name("Singh","Robin", "");
        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact);
        Name actualName = acc.getContact();
        Assert.assertEquals(contact, actualName);
    }

    @Test
    public void setContact() throws Exception {
        Name contact = new Name("Singh","Robin", "");
        Name contact2 = new Name("Taneja","Ram", "J.");
        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact);
        acc.setContact(contact2);

        Assert.assertTrue(contact2.equals(acc.getContact()));
    }

    @Test
    public void isBillable() throws Exception {

        Name contact = new Name("Singh","Robin", "");
        ClientAccount acc = new ClientAccount("XYZ Consultant",  contact);
        boolean actual = acc.isBillable();
        Assert.assertTrue(actual);

    }

}