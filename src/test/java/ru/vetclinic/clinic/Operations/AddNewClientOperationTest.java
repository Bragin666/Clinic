package ru.vetclinic.clinic.Operations;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.IncorrectDataEntryException;
import ru.vetclinic.clinic.Exceptions.ThisClientNameAlreadyExistsException;
import ru.vetclinic.clinic.Main.Client;
import ru.vetclinic.clinic.Main.Clinic;

import static org.junit.Assert.assertTrue;

/**
 * Дописан
 * Created by Djony on 21.07.2016.
 */
public class AddNewClientOperationTest {
    @Test
    public void simpleAdding() throws Exception {
        Clinic clinic = new Clinic();
        Client client = new Client("vova");

        String s = "addnewclient vova";
        clinic.doOperation(s);

        assertTrue(clinic.getClients().contains(client));
    }

    @Test(expected = ThisClientNameAlreadyExistsException.class)
    public void thisClientNameAlreadyExistsException() throws Exception {
        Clinic clinic = new Clinic();

        String s = "addnewclient vova";
        clinic.doOperation(s);
        clinic.doOperation(s);
    }

    @Test(expected = IncorrectDataEntryException.class)
    public void incorrectDataEntryException()throws Exception {
        Clinic clinic = new Clinic();

        String s = "addnewclient";
        clinic.doOperation(s);
    }
}