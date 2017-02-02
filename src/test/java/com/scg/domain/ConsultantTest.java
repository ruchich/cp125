package com.scg.domain;

import com.scg.util.Name;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/1/2017.
 */
public class ConsultantTest {
    @Test
    public void getName()  {
        Name name = new Name("Robin", "Singh","");
        Consultant consultant = new Consultant(name);
        Name actual = consultant.getName();
        Assert.assertEquals(name,actual);


    }

}