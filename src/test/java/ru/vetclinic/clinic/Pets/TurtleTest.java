package ru.vetclinic.clinic.Pets;

import org.junit.Test;
import ru.vetclinic.clinic.Main.Client;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Djony on 14.07.2016.
 */
public class TurtleTest {
    @Test
    public void cat() throws Exception {
        Client client = new Client("Vova");
        Turtle turtle = new Turtle(client, "Dysya");

        assertThat(turtle.getOwner(), is(client));
        assertThat(turtle.getName(), is("Dysya"));
        assertThat(turtle.getPetType(), is("turtle"));
    }

    @Test
    public void makeSound() throws Exception {
        Client client = new Client("Vova");
        Turtle turtle = new Turtle(client, "Dysya");

        assertThat(turtle.makeSound(), is("Bul-bul"));
    }
}