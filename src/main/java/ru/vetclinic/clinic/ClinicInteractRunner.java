package ru.vetclinic.clinic;

import ru.vetclinic.clinic.Exceptions.*;
import ru.vetclinic.clinic.Streams.ClinicInputStream;
import ru.vetclinic.clinic.Streams.ClinicOutputStream;

import java.io.IOException;

/**
 * Основной класс, запускающий нашу клинику
 * Created by Djony on 21.06.2016.
 */
public class ClinicInteractRunner {
    public static void main(String[] args) {
        ClinicInputStream inputStream = new ClinicInputStream();
        ClinicOutputStream outputStream = new ClinicOutputStream();
        Clinic clinic = new Clinic();

        while (true) {
            try {
                outputStream.print("Enter next command:");
                String s = inputStream.getNext();
                String[] words = s.split(" ");
                String message = clinic.doOperation(words);
                outputStream.print(message);
            }
            catch (IOException e) { outputStream.print("Error data entry!"); }
            catch (UnknownCommandException e) { outputStream.print("Exception! Unknown command!"); }
            catch (IncorrectDataEntryException e) { outputStream.print("Exception! Incorrect data entry!"); }
            catch (ThisClientNameAlreadyExistsException e) { outputStream.print("Exception! The client with the same name already exists!"); }
            catch (ClientDoesntExistException e) { outputStream.print("Exception! The client with this id doesn’t exist!"); }
            catch (PetDoesntExistException e) { outputStream.print("Exception! The pet with this id doesn’t exist!"); }
            catch (UnknownPetTypeException e) {outputStream.print("Exception! Unknown pet type!");}

            catch (MyException e) {outputStream.print("Unknown exception!");}
        }
    }
}
