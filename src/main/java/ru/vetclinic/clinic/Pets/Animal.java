package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;

/**
 * Абстрактный класс Животное, реализующий методы интерфеса Питомец
 * Created by Djony on 05.07.2016.
 */
public abstract class Animal implements Pet {
    /** Идентификационный номер животного */
    private final int id;
    /** Кличка кошки */
    private String name;
    /** Вдажелец кошки */
    private final Client owner;

    /**
     * Конструктор животного
     * @param id Идентификационный номер
     * @param owner Владелец
     * @param name Кличка
     */
    public Animal(int id, Client owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }

    /**
     * Возвращает идентификационный номер питомца
     */
    public int getId() {
        return 0;
    }

    /**
     * Возвращает кличку питомца
     */
    public String getName() {
        return null;
    }

    /**
     * Изменяет кличку питомца
     *
     * @param name Новая кличка питомца
     */
    public void setName(String name) {

    }

    /**
     * Возвращает хозяина питомца
     */
    public Client getOwner() {
        return null;
    }

    /**
     * Подать голос
     */
    public abstract void makeSound();
}
