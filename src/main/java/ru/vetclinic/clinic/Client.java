package ru.vetclinic.clinic;

import ru.vetclinic.clinic.Pets.Pet;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Клиент ветеринарной клиники, владелец домашнего питомца
 * Created by Djony on 15.06.2016.
 */
@Entity
public class Client implements Serializable {
    /** Идентификационнный номер клиента */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /** Имя клиента */
    private String name;
    /** Питомец */
    @Transient
    private Pet pet;

    public Client() {}

    /**
     * Основной конструктор класса
     * @param name Имя клиента
     */
    public Client(String name) {
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
     * Возвращает питомца
     * @return Питомец
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Возвращает питомца
     * @param pet Питомец
     */
    public void setPet(final Pet pet) {
        this.pet = pet;
    }
}
