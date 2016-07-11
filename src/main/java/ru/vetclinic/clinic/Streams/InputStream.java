package ru.vetclinic.clinic.Streams;

import java.io.IOException;

/**
 * Входящий поток данных
 * Created by Djony on 10.07.2016.
 */
public interface InputStream {
    /**
     * Возвращает введенную строку
     * @return Введенная строка
     */
    String getNext() throws IOException;
}
