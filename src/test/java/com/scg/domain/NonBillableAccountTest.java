package com.scg.domain;

import com.scg.util.Name;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/1/2017.
 */
public class NonBillableAccountTest {
    @Test
    public void getName()  {
        NonBillableAccount acc =  NonBillableAccount.VACATION;
        String actualName = acc.getName();
        Assert.assertTrue("Vacation".equals(actualName));

    }

    @Test
    public void isBillable()  {

        NonBillableAccount acc =  NonBillableAccount.VACATION;
       Assert.assertFalse(acc.isBillable());
    }

}