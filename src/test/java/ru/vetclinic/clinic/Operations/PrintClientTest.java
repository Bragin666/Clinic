package ru.vetclinic.clinic.Operations;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.ClientDoesntExistException;
import ru.vetclinic.clinic.Exceptions.IncorrectDataEntryException;
import ru.vetclinic.clinic.Main.Client;
import ru.vetclinic.clinic.Main.Clinic;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Dog;
import ru.vetclinic.clinic.Pets.Pet;

import static org.junit.Assert.*;

/**
 * Created by Djony on 31.07.2016.
 */
public class PrintClientTest {
    @Test
    public void simpleChanging() throws Exception {
        Clinic clinic = new Clinic();

        Client client1 = new Client("vova");
        Client client2 = new Client("tolya");
        Pet pet1 = new Dog(client1, "Rex");
        Pet pet2 = new Cat(client1, "Bursik");
        Pet pet3 = new Dog(client2, "Tuzik");
        Pet pet4 = new Cat(client2, "Musya");

        clinic.doOperation("addnewclient vova");
        clinic.doOperation("addnewclient tolya");
        clinic.doOperation("addnewpet vova dog Rex");
        clinic.doOperation("addnewpet vova cat Bursik");
        clinic.doOperation("addnewpet tolya dog Tuzik");
        clinic.doOperation("addnewpet tolya cat Musya");

        String s = client1.toString();

        assertEquals(clinic.doOperation("printclient vova"), s);
    }

    @Test(expected = IncorrectDataEntryException.class)
    public void incorrectDataEntryException() throws Exception {
        Clinic clinic = new Clinic();

        String s = "printclient";
        clinic.doOperation(s);
    }

    @Test(expected = ClientDoesntExistException.class)
    public void clientDoesntExistException() throws Exception {
        Clinic clinic = new Clinic();

        String s = "printclient misha";
        clinic.doOperation(s);
    }
}