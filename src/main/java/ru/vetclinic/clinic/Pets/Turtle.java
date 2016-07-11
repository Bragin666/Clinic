package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;

/**
 * Домашняя черепаха
 * Created by Djony on 05.07.2016.
 */
public class Turtle extends Animal {
    /**
     * Конструктор черепахи
     * @param owner Владелец
     * @param name Кличка
     */
    public Turtle(Client owner, String name) {
        super(owner, name);
        this.petType = "turtle";
    }

    /**
     * Подать голос
     */
    public String makeSound() {
        return "Bul-bul";
    }
}
