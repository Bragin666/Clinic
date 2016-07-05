package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;

/**
 * Домашняя черепаха
 * Created by Djony on 05.07.2016.
 */
public class Turtle extends Animal {
    /**
     * Конструктор черепахи
     * @param id Идентификационный номер
     * @param owner Владелец
     * @param name Кличка
     */
    public Turtle(int id, Client owner, String name) {
        super(id, owner, name);
        owner.setPet(this);
    }

    /**
     * Подать голос
     */
    public void makeSound() {
        System.out.println("Bul-bul");
    }
}
