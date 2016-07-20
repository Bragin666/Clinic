package ru.vetclinic.clinic;

import org.junit.Test;
import ru.vetclinic.clinic.Exceptions.UnknownCommandException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Djony on 17.07.2016.
 */
public class ClinicTest {
    @Test(expected = UnknownCommandException.class)
    public void doOperation() throws Exception {
        Clinic clinic = new Clinic();

        String[] words1 = {"printallpets"};
        String[] words2 = {"kln.ksnb.knlk"};

        assertThat(clinic.doOperation(words1), is(""));
        clinic.doOperation(words2);
    }

}