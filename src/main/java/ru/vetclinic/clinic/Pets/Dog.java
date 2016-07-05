package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;
import ru.vetclinic.clinic.Pet;

/**
 * Домашняя собака
 * Created by Djony on 05.07.2016.
 */
public class Dog implements Pet {
    /** Идентификационный номер собаки */
    private final int id;
    /** Кличка собаки */
    private String name;
    /** Вдажелец собаки */
    private final Client owner;

    /**
     * Конструктор собаки
     * @param id Идентификационный номер
     * @param owner Владелец
     * @param name Кличка
     */
    public Dog(int id, Client owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        owner.setPet(this);
    }

    /**
     * Возвращает идентификационный номер собаки
     */
    public int getId() {
        return this.id;
    }

    /**
     * Возвращает кличку собаки
     */
    public String getName() {
        return this.name;
    }

    /**
     * Изменяет кличку собаки
     *
     * @param name Новая кличка питомца собаки
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает хозяина собаки
     */
    public Client getOwner() {
        return this.owner;
    }

    /**
     * Подать голос
     */
    public void makeSound() {
        System.out.println("Gav-gav");
    }
}
