package ru.vetclinic.clinic;

import ru.vetclinic.clinic.Exceptions.MyException;

/**
 * Операция
 * Created by Djony on 05.07.2016.
 */
public interface Operation {
    /**
     * Выполняет операцию
     * @param words Распарсенные слова операции
     * @return Сообщение о выполнении или какой-то текст
     */
    String doOperation(String[] words) throws MyException;
}
