package ru.vetclinic.clinic.Operations;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.IncorrectDataEntryException;
import ru.vetclinic.clinic.Exceptions.PetDoesntExistException;
import ru.vetclinic.clinic.Main.Client;
import ru.vetclinic.clinic.Main.Clinic;

import static org.junit.Assert.*;

/**
 * Закончен
 * Created by Djony on 30.07.2016.
 */
public class FindClientByPetNameTest {
    @Test
    public void simpleFinding() throws Exception {
        Clinic clinic = new Clinic();
        Client client1 = new Client("vova");

        String s1 = "addnewclient vova";
        clinic.doOperation(s1);
        String s2 = "addnewpet vova cat Musya";
        clinic.doOperation(s2);
        String s3 = "findclient Musya";

        assertEquals(clinic.doOperation(s3), "vova");
    }

    @Test(expected = IncorrectDataEntryException.class)
    public void IncorrectDataEntryExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s = "findclient Musya Trysaya";
        clinic.doOperation(s);
    }

    @Test(expected = PetDoesntExistException.class)
    public void PetDoesntExistExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s = "findclient Musya";
        clinic.doOperation(s);
    }
}