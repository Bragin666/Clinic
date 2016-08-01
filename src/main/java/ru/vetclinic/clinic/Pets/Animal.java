package ru.vetclinic.clinic.Pets;

import ru.vetclinic.clinic.Main.Client;
import ru.vetclinic.clinic.Temperal.IDGenerator;

/**
 * Абстрактный класс Животное, реализующий методы интерфеса Питомец
 * Created by Djony on 05.07.2016.
 */
abstract class Animal implements Pet {
    /** Идентификационный номер животного */
    private int id;
    /** Тип животного */
    String petType;
    /** Кличка животного */
    private String name;
    /** Хозяин животного */
    private Client owner;

    /**
     * Конструктор животного
     * @param owner Владелец
     * @param name Кличка
     */
    Animal(Client owner, String name) {
        this.id = IDGenerator.getPetId();
        this.owner = owner;
        this.name = name;
        if (owner != null) owner.addPet(this);
    }

    /**
     * Возвращает идентификационный номер питомца
     * @return
     */
    public int getId() {
        return id;
    }

    /** Возвращает тип животного */
    public String getPetType() {
        return petType;
    }

    /**
     * Возвращает кличку питомца
     */
    public String getName() {
        return name;
    }

    /**
     * Изменяет кличку питомца
     *
     * @param name Новая кличка питомца
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает хозяина питомца
     */
    public Client getOwner() {
        return owner;
    }

    /** Изменяеи хозяина */
    public void setOwner(Client client) {this.owner = client; }

    /**
     * Подать голос
     */
    public abstract String makeSound();

    @Override
    public String toString() {
        return "petType = " + petType + ", name = " + name + ", owner = " +
                (owner == null ? "null" : owner.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        return getName().equals(animal.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
