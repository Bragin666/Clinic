package ru.vetclinic.clinic.Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Рабочий поток входных данных
 * Created by Djony on 10.07.2016.
 */
public class ClinicInputStream implements InputStream {
    /**
     * Возвращает введенную строку
     *
     * @return Введенная строка
     */
    public String getNext() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        return reader.readLine();
    }
}
