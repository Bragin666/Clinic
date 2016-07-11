package ru.vetclinic.clinic;

/**
 * Генератор Id
 * Created by Djony on 10.07.2016.
 */
public class IDGenerator {
    private static int clientID;
    private static int petID;

    static {
        clientID = 0;
        petID = 0;
    }

    /**
     * Возвращает идентификационный номер клиента
     * @return id клиента
     */
    public static int getClientId(){
        return ++clientID;
    }

    /**
     * Возвращает идентификационный номер питомца
     * @return id питомца
     */
    public static int getPetId(){
        return ++petID;
    }
}
