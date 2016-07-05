package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;

/**
 * Питомец клиента ветеринарной клиники
 * Created by Djony on 15.06.2016.
 */
public interface Pet {
    /**
     * Возвращает идентификационный номер питомца
     */
    int getId();

    /**
     * Возвращает кличку питомца
     */
    String getName();

    /**
     * Изменяет кличку питомца
     * @param name Новая кличка питомца
     */
    void setName(String name);

    /**
     * Возвращает хозяина питомца
     */
    Client getOwner();

    /**
     * Подать голос
     */
    void makeSound();
}