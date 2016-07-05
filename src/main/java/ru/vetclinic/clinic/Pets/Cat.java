package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;
import ru.vetclinic.clinic.Pet;

/**
 * Домашняя кошка
 * Created by Djony on 15.06.2016.
 */
public class Cat implements Pet{
    /** Идентификационный номер кошки */
    private final int id;
    /** Кличка кошки */
    private String name;
    /** Вдажелец кошки */
    private final Client owner;

    /**
     * Конструктор кошки
     * @param id Идентификационный номер
     * @param owner Владелец
     * @param name Кличка
     */
    public Cat(int id, Client owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        owner.setPet(this);
    }

    /**
     * Возвращает идентификационный номер кошки
     */
    public int getId() {
        return this.id;
    }

    /**
     * Возвращает кличку кошки
     */
    public String getName() {
        return this.name;
    }

    /**
     * Изменяет кличку кошки
     *
     * @param name Новая кличка кошки
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает хозяина кошки
     */
    public Client getOwner() {
        return this.owner;
    }

    /**
     * Подать голос
     */
    public void makeSound() {
        System.out.println("Myau-myau");
    }
}
