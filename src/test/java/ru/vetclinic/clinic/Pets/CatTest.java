package ru.vetclinic.clinic.Pets;

import org.junit.Test;
import ru.vetclinic.clinic.Main.Client;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Djony on 14.07.2016.
 */
public class CatTest {
    @Test
    public void cat() throws Exception {
        Client client = new Client("Vova");
        Cat cat = new Cat(client, "Dysya");

        assertThat(cat.getOwner(), is(client));
        assertThat(cat.getName(), is("Dysya"));
        assertThat(cat.getPetType(), is("cat"));
    }

    @Test
    public void makeSound() throws Exception {
        Client client = new Client("Vova");
        Cat cat = new Cat(client, "Dysya");

        assertThat(cat.makeSound(), is("Miu-miu"));
    }
}