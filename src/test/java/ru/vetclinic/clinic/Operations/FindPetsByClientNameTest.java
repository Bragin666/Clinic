package ru.vetclinic.clinic.Operations;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.ClientDoesntExistException;
import ru.vetclinic.clinic.Exceptions.IncorrectDataEntryException;
import ru.vetclinic.clinic.Exceptions.PetDoesntExistException;
import ru.vetclinic.clinic.Main.Client;
import ru.vetclinic.clinic.Main.Clinic;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Pet;

import static org.junit.Assert.*;

/**
 * Закончен
 * Created by Djony on 30.07.2016.
 */
public class FindPetsByClientNameTest {
    @Test
    public void simpleFinding() throws Exception {
        Clinic clinic = new Clinic();
        Client client1 = new Client("vova");
        Client client2 = new Client("misha");
        Pet pet = new Cat(client1, "Musya");

        String s1 = "addnewclient vova";
        clinic.doOperation(s1);
        String s2 = "addnewclient misha";
        clinic.doOperation(s2);
        String s3 = "addnewpet vova dog Rex";
        clinic.doOperation(s3);
        String s4 = "addnewpet misha cat Musya";
        clinic.doOperation(s4);
        String s5 = "addnewpet vova turtle Leonard";
        clinic.doOperation(s5);
        String s6 = "findpets misha";

        assertEquals(clinic.doOperation(s6), "cat Musya\n");
    }

    @Test(expected = IncorrectDataEntryException.class)
    public void IncorrectDataEntryExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s = "findpets vova tolya";
        clinic.doOperation(s);
    }

    @Test(expected = ClientDoesntExistException.class)
    public void PetDoesntExistExceptionTest() throws Exception {
        Clinic clinic = new Clinic();

        String s = "findpets vova";
        clinic.doOperation(s);
    }
}