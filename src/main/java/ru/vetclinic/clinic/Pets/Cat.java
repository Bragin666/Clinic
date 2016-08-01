package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Main.Client;

/**
 * Домашняя кошка
 * Created by Djony on 15.06.2016.
 */
public class Cat extends Animal {
    /**
     * Конструктор кошки
     * @param owner Хозяин, владелец, клиент ветеринарной клиники
     * @param name Кличка
     */
    public Cat(Client owner, String name) {
        super(owner, name);
        this.petType = "cat";
    }

    /**
     * Подать голос
     */
    public String makeSound() {
        return "Miu-miu";
    }
}
