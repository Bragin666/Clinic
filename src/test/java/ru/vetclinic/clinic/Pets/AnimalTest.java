package ru.vetclinic.clinic.Pets;

import org.junit.Test;
import ru.vetclinic.clinic.Main.Client;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Djony on 14.07.2016.
 */
public class AnimalTest {

    @Test
    public void toStringTest() throws Exception {
        Client client = new Client("Vova");
        Animal animal = new Cat(client, "Dysya");

        String s = "petType = cat, name = Dysya, owner = " + client.getName();

        assertThat(animal.toString(), is(s));
    }

}