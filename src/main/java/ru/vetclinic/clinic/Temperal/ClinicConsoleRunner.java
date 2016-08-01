package ru.vetclinic.clinic.Temperal;

import ru.vetclinic.clinic.Exceptions.*;
import ru.vetclinic.clinic.Main.Clinic;
import ru.vetclinic.clinic.Streams.ClinicInputStream;
import ru.vetclinic.clinic.Streams.ClinicOutputStream;
import ru.vetclinic.clinic.Streams.InputStream;
import ru.vetclinic.clinic.Streams.OutputStream;

import java.io.IOException;

/**
 * Основной класс, запускающий нашу клинику
 * Created by Djony on 21.06.2016.
 */
public class ClinicConsoleRunner {
    /** Параметр - поток входящих данных */
    private InputStream inputStream;
    /** Параметр - поток исходящих данных */
    private OutputStream outputStream;

    /**
     *  Конструктор
     * @param inputStream поток входящих данных
     * @param outputStream поток исходящих данных
     */
    public ClinicConsoleRunner(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    /** Мэйн */
    public static void main(String[] args) {
        ClinicInputStream inputStream = new ClinicInputStream();
        ClinicOutputStream outputStream = new ClinicOutputStream();

        ClinicConsoleRunner consoleRunner = new ClinicConsoleRunner(inputStream, outputStream);
        consoleRunner.run();
    }

    /**
     * Метод запускающий нашу клинику
     */
    public void run() {
        Clinic clinic = new Clinic();

        while (true) {
            try {
                outputStream.print("Enter next command:");
                String s = inputStream.getNext();
                String message = clinic.doOperation(s);
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
