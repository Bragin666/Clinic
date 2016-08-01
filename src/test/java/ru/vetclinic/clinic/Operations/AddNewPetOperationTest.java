package ru.vetclinic.clinic.Operations;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.*;
import ru.vetclinic.clinic.Main.Clinic;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Pet;

import static org.junit.Assert.*;

/**
 * Дописан
 * Created by Djony on 28.07.2016.
 */
public class AddNewPetOperationTest {
    @Test
    public void simpleAdding() throws Exception {
        Clinic clinic = new Clinic();
        Pet pet = new Cat(null, "musya");

        String s = "addnewpet null cat musya";
        clinic.doOperation(s);

        assertTrue(clinic.getPets().contains(pet));
    }

    @Test(expected = IncorrectDataEntryException.class)
    public void incorrectDataEntryException() throws Exception {
        Clinic clinic = new Clinic();

        String s = "addnewpet";
        clinic.doOperation(s);
    }

    @Test(expected = ClientDoesntExistException.class)
    public void clientDoesntExistException() throws Exception {
        Clinic clinic = new Clinic();

        String s = "addnewpet 1 cat musya";
        clinic.doOperation(s);
    }

    @Test(expected = UnknownPetTypeException.class)
    public void unknownPetTypeException() throws Exception {
        Clinic clinic = new Clinic();

        String s = "addnewpet null fox musya";
        clinic.doOperation(s);
    }

    @Test(expected = ThisPetNameAlreadyExistsException.class)
    public void thisPetNameAlreadyExistsException() throws Exception {
        Clinic clinic = new Clinic();

        String s = "addnewpet null cat musya";
        clinic.doOperation(s);
        clinic.doOperation(s);
    }
}