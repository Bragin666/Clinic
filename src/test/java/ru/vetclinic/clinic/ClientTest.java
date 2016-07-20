package ru.vetclinic.clinic;

import org.junit.Test;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Dog;
import ru.vetclinic.clinic.Pets.Pet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Djony on 17.07.2016.
 */
public class ClientTest {
    @Test
    public void client() throws Exception {
        Client client = new Client("Vova");

        assertThat(client.getName(), is("Vova"));
        assertThat(client.getId() != 0, is(true));
    }

    @Test
    public void addPet() throws Exception {
        Client client = new Client("Vova");
        Dog dog = new Dog(null, "Dysya");

        client.addPet(dog);

        assertThat(client.getPets().contains(dog), is(true));
    }

    @Test
    public void removePet() throws Exception {
        Client client = new Client("Vova");
        Dog dog = new Dog(client, "Dysya");

        client.removePet(dog);

        assertThat(client.getPets().contains(dog), is(false));
    }

    @Test
    public void toStringTest() throws Exception {
        Client client = new Client("Vova");
        Dog dog = new Dog(client, "Dysya");
        Cat cat = new Cat(client, "Mysya");

        String s = "id= " + client.getId() + ", name= " + "Vova" + "\nPets :" +
            "\n   " + "id = " + dog.getId() + ", petType = " + "dog" + ", name = " + "Dysya" +
            "\n   " + "id = " + cat.getId() + ", petType = " + "cat" + ", name = " + "Mysya";

        assertThat(client.toString(), is(s));
    }

}