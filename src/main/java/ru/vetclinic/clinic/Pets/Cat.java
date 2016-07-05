package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Client;

/**
 * Домашняя кошка
 * Created by Djony on 15.06.2016.
 */
public class Cat extends Animal {
    /**
     * Конструктор кошки
     * @param id Идентификационный номер
     * @param owner Хозяин, владелец, клиент ветеринарной клиники
     * @param name Кличка
     */
    public Cat(int id, Client owner, String name) {
        super(id, owner, name);
        owner.setPet(this);
    }

    /**
     * Подать голос
     */
    public void makeSound() {
        System.out.println("Miu-miu");
    }
}
