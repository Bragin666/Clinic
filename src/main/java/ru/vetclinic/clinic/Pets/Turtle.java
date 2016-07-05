package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;
import ru.vetclinic.clinic.Pet;

/**
 * Домашняя черепаха
 * Created by Djony on 05.07.2016.
 */
public class Turtle implements Pet {
    /** Идентификационный номер черепахи */
    private final int id;
    /** Кличка черепахи */
    private String name;
    /** Вдажелец черепахи */
    private final Client owner;

    /**
     * Конструктор черепахи
     * @param id Идентификационный номер
     * @param owner Владелец
     * @param name Кличка
     */
    public Turtle(int id, Client owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        owner.setPet(this);
    }

    /**
     * Возвращает идентификационный номер черепахи
     */
    public int getId() {
        return this.id;
    }

    /**
     * Возвращает кличку черепахи
     */
    public String getName() {
        return this.name;
    }

    /**
     * Изменяет кличку черепахи
     *
     * @param name Новая кличка питомца черепахи
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает хозяина черепахи
     */
    public Client getOwner() {
        return this.owner;
    }

    /**
     * Подать голос
     */
    public void makeSound() {
        System.out.println("Bul-bul");
    }
}
