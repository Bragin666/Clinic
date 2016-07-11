package ru.vetclinic.clinic.Streams;

/**
 * Рабочий поток выходных данных
 * Created by Djony on 10.07.2016.
 */
public class ClinicOutputStream implements OutputStream {
    /**
     * Выводит строку
     *
     * @param s Строка
     */
    public void print(String s) {
        System.out.println(s);
    }
}
