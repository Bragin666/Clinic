package ru.vetclinic.clinic.Operations;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.ClientDoesntExistException;
import ru.vetclinic.clinic.Exceptions.IncorrectDataEntryException;
import ru.vetclinic.clinic.Main.Clinic;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Pet;

import static org.junit.Assert.*;

/**
 * Дописан
 * Created by Djony on 28.07.2016.
 */
public class RemoveClientTest {
    @Test
    public void simpleRemoving() throws Exception {
        Clinic clinic = new Clinic();
        Pet pet = new Cat(null, "Musya");

        String s1 = "addnewclient vova";
        clinic.doOperation(s1);
        String s2 = "addnewpet vova cat Musya";
        clinic.doOperation(s2);
        String s3 = "removeclient vova";


        assertTrue(clinic.getPet("Musya").getOwner() != null);
        assertEquals(clinic.getClients().size(), 1);
        clinic.doOperation(s3);
        assertEquals(clinic.getClients().size(), 0);
        assertTrue(clinic.getPet("Musya").getOwner() == null);
    }

    @Test(expected = IncorrectDataEntryException.class)
    public void IncorrectDataEntryExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s2 = "removeclient misha ghj";
        clinic.doOperation(s2);
    }

    @Test(expected = ClientDoesntExistException.class)
    public void ClientDoesntExistExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s1 = "addnewclient vova";
        clinic.doOperation(s1);
        String s2 = "removeclient misha";
        clinic.doOperation(s2);

    }
}