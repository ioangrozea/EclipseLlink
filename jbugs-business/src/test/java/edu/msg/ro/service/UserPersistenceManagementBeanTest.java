package edu.msg.ro.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserPersistenceManagementBeanTest {

    UserManagementBean bean = new UserManagementBean();

    @Test
    public void generateUsername_expectedMarini() {
        String username = bean.generateUsername("Ion", "Marin");
        assertTrue("Expected marini but found " + username, username.equals("marini"));
    }

    @Test
    public void generateUsername_expectedIonion() {
        String username = bean.generateUsername("Ion", "Ion");
        assertTrue("Expected ionion but found " + username, username.equals("ionion"));
    }

    @Test
    public void generateUsername_expectidPetric() {
        String username = bean.generateUsername("Calin", "Petrindean");
        assertTrue("Expected petric but found " + username, username.equals("petric"));
    }

    @Test
    public void generateUsername_expectedba0000() {
        String username = bean.generateUsername("a", "b");
        assertTrue("Expected ba0000 but found " + username, username.equals("ba0000"));
    }


}