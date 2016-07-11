package ru.vetclinic.clinic;

import ru.vetclinic.clinic.Pets.Pet;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Клиент ветеринарной клиники, владелец домашнего питомца
 * Created by Djony on 15.06.2016.
 */
public class Client implements Serializable {
    /** Идентификационный номер клиента */
    private int id;
    /** Имя клиента */
    private String name;
    /** Питомец */
    private Set<Pet> pets = new HashSet<Pet>();

    /**
     * Основной конструктор класса
     * @param name Имя клиента
     */
    public Client(String name) {
        this.id = IDGenerator.getClientId();
        this.name = name;
    }

    /**
     * Возвращает идентификационный номер клиента
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя клиента
     * @return Имя
     */
    public String getName() {
        return name;
    }

    /**
     * Присваивает клиенту другое имя
     * @param name Новое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает всех питомцев
     * @return Питомец
     */
    public Set<Pet> getPets() {
        return pets;
    }

    /**
     * Добавляет нового питомца питомца
     * @param pet Питомец
     */
    public void addPet(final Pet pet) {
        pets.add(pet);
    }

    /**
     * Удалеет питомца у клиента
     * @param pet Питомец
     */
    public void removePet(Pet pet) {
        pets.remove(pet);
    }

    @Override
    public String toString() {
        String s = "id= " + id + ", name= " + name + "\nPets :";
        for (Pet pet : pets) s = s + "\n   " + "id = " + pet.getId() + ", petType = " + pet.getPetType()
                + ", name = " + pet.getName();
        return  s;
    }
}
