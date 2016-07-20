package ru.vetclinic.clinic.Pets;

import org.junit.Test;
import ru.vetclinic.clinic.Client;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Djony on 14.07.2016.
 */
public class DogTest {
    @Test
    public void cat() throws Exception {
        Client client = new Client("Vova");
        Dog dog = new Dog(client, "Dysya");

        assertThat(dog.getOwner(), is(client));
        assertThat(dog.getName(), is("Dysya"));
        assertThat(dog.getPetType(), is("dog"));
    }

    @Test
    public void makeSound() throws Exception {
        Client client = new Client("Vova");
        Dog dog = new Dog(client, "Dysya");

        assertThat(dog.makeSound(), is("Gav-gav"));
    }
}