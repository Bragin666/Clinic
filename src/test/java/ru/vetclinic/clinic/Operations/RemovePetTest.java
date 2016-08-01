package ru.vetclinic.clinic.Operations;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.ClientDoesntExistException;
import ru.vetclinic.clinic.Exceptions.IncorrectDataEntryException;
import ru.vetclinic.clinic.Exceptions.PetDoesntExistException;
import ru.vetclinic.clinic.Main.Clinic;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Pet;

import static org.junit.Assert.*;

/**
 * Created by Djony on 30.07.2016.
 */
public class RemovePetTest {
    @Test
    public void simpleRemoving() throws Exception {
        Clinic clinic = new Clinic();
        Pet pet = new Cat(null, "Musya");

        String s1 = "addnewclient vova";
        clinic.doOperation(s1);
        String s2 = "addnewpet vova cat Musya";
        clinic.doOperation(s2);

        assertTrue(clinic.getClient("vova").getPets().contains(pet));
        clinic.doOperation("removepet Musya");
        assertEquals(clinic.getPets().size(), 0);
        assertFalse(clinic.getClient("vova").getPets().contains(pet));
    }

    @Test(expected = IncorrectDataEntryException.class)
    public void IncorrectDataEntryExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s = "removepet misha ghj";
        clinic.doOperation(s);
    }

    @Test(expected = PetDoesntExistException.class)
    public void PetDoesntExistExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s2 = "removepet Bursic";
        clinic.doOperation(s2);

    }

}