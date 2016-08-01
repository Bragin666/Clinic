package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Main.Client;

/**
 * Домашняя собака
 * Created by Djony on 05.07.2016.
 */
public class Dog extends Animal {
    /**
     * Конструктор собаки
     * @param owner Хозяин, владелец, клиент ветеринарной клиники
     * @param name Кличка
     */
    public Dog(Client owner, String name) {
        super(owner, name);
        this.petType = "dog";
    }

    /**
     * Подать голос
     */
    public String makeSound() {
        return "Gav-gav";
    }
}
