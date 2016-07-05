package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;

/**
 * Домашняя собака
 * Created by Djony on 05.07.2016.
 */
public class Dog extends Animal {
    /**
     * Конструктор собаки
     * @param id Идентификационный номер
     * @param owner Хозяин, владелец, клиент ветеринарной клиники
     * @param name Кличка
     */
    public Dog(int id, Client owner, String name) {
        super(id, owner, name);
        owner.setPet(this);
    }

    /**
     * Подать голос
     */
    public void makeSound() {
        System.out.println("Gav-gav");
    }
}
