package ru.vetclinic.clinic;

import ru.vetclinic.clinic.Exceptions.*;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Dog;
import ru.vetclinic.clinic.Pets.Pet;
import ru.vetclinic.clinic.Pets.Turtle;

import java.util.*;

/**
 * Ветеренарная клиника
 */
class Clinic {
    /** Список клиентов клиники */
    private final Set<Client> clients = new HashSet<Client>();
    /** Список питомцев в клинике */
    private final Set<Pet> pets = new HashSet<Pet>();

    /** Список всех доступных операций */
    private final HashMap<String, Operation> availableOperations = new HashMap<String, Operation>();
    /** Список всех доступных видом животных */
    private final HashMap<String, Class> availablePets = new HashMap<String, Class>();


    /** Конструктор */
    Clinic() {
        this.loadNewOperation("quit", new QuitOperation());
        this.loadNewOperation("addnewclient", new AddNewClientOperation());
        this.loadNewOperation("addnewpet", new AddNewPetOperation());
        this.loadNewOperation("removeclient", new RemoveClient());
        this.loadNewOperation("removepet", new RemovePet());
        this.loadNewOperation("findclient", new FindClientByPetName());
        this.loadNewOperation("findpets", new FindPetsByClientName());
        this.loadNewOperation("changeclientname", new ChangeClientName());
        this.loadNewOperation("changepetname", new ChangePetName());
        this.loadNewOperation("printallclients", new PrintAllClients());
        this.loadNewOperation("printallpets", new PrintAllPets());
        this.loadNewOperation("printclient", new PrintClient());
        this.loadNewOperation("printpet", new PrintPet());

        this.loadNewPet("cat", Cat.class);
        this.loadNewPet("dog", Dog.class);
        this.loadNewPet("turtle", Turtle.class);
    }

    /**
     * Выполняет операцию
     * @param words Слова
     * @return Текс или сообщение
     * @throws MyException Различные исключения
     */
    public String doOperation(String[] words) throws MyException {
        String message = "";
        if (availableOperations.containsKey(words[0])) {
            message = availableOperations.get(words[0]).doOperation(words);
        }
        else throw new UnknownCommandException();
        return message;
    }

    /**
     * Добавляет новую операцию в список доступных операций
     * @param operation Добавляемая операция
     */
    private void loadNewOperation(String command, Operation operation) {
        availableOperations.put(command, operation);
    }

    /**
     * Добавляет нового питомца в список доступных питомцев
     * @param className Название класса
     * @param classType Класс
     */
    private void loadNewPet(String className, Class classType) {
        availablePets.put(className,classType);
    }

    /**
     * Проверяет, не используется ли уже такое имя клиента
     * @param name Имя клиента
     * @return Валидность имени
     */
    private boolean isThisClientNameAlreadyExists(String name){
        boolean result = false;
        for (Client client : clients) {
            if (client.getName().equals(name)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Проверяет, не используется ли уже такое имя питомца
     * @param name Имя питомца
     * @return Валидность имени
     */
    private boolean isThisPetNameAlreadyExists(String name){
        boolean result = false;
        for (Pet pet : pets) {
            if (pet.getName().equals(name)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает клиента по имени
     * @param id Идентификационный номер клиента
     * @return Клиента или null, если такого клиента нету
     */
    private Client getClient(int id) throws ClientDoesntExistException {
        Client result = null;
        for (Client client : clients) {
            if (client.getId() == id) {
                result = client;
                break;
            }
        }
        if (result == null) throw new ClientDoesntExistException();
        return result;
    }

    /**
     * Возвращает питомца по имени
     * @param id Идентификационный номер питомца
     * @return Клиента или null, если такого клиента нету
     */
    private Pet getPet(int id) throws PetDoesntExistException {
        Pet result = null;
        for (Pet pet : pets) {
            if (pet.getId() == id) {
                result = pet;
                break;
            }
        }
        if (result == null) throw new PetDoesntExistException();
        return result;
    }

    /**
     * Распарсивает буквенное значение id в сисловое
     * @param word Буквенное значение
     * @return Числовое значение
     */
    private int parseInt(String word) throws IncorrectDataEntryException {
        int id;
        try {
            id = Integer.parseInt(word);
        }
        catch (Exception e) {
            throw new IncorrectDataEntryException();
        }
        return id;
    }

    /**
     * Операция завершения работы
     */
    private class QuitOperation implements Operation {
        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 1) throw new IncorrectDataEntryException();

            System.exit(0);

            return "";
        }
    }

    /**
     * Операция добавления нового клиента
     */
    private class AddNewClientOperation implements Operation {
        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         */
        public String doOperation(String[] words) throws MyException {
            if (!(words.length == 2)) throw new IncorrectDataEntryException();

            String name = words[1];
            if (Clinic.this.isThisClientNameAlreadyExists(name)) throw new ThisClientNameAlreadyExistsException();

            Client client = new Client(name);
            clients.add(client);

            return "New client is successfully added! id = " + client.getId();
        }
    }

    /**
     * Операция добавления нового питомца
     */
    private class AddNewPetOperation implements Operation {
        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         */
        public String doOperation(String[] words) throws MyException {
            if (!(words.length == 4)) throw new IncorrectDataEntryException();

            Client client;
            if (words[1].equals("null")) client = null;
            else {
                int clientId = Clinic.this.parseInt(words[1]);
                client = Clinic.this.getClient(clientId);
            }
            if (!clients.contains(client)) throw new ClientDoesntExistException();

            String petType = words[2];
            if (!availablePets.containsKey(petType)) throw new UnknownPetTypeException();

            String petName = words[3];
            if (Clinic.this.isThisPetNameAlreadyExists(petName)) throw new ThisPetNameAlreadyExistsException();

            Pet pet = null;
            try {
                pet = (Pet) availablePets.get(petType).getConstructor(Client.class, String.class).newInstance(client, petName);
            }
            catch (Exception e) {
                System.out.println("Unknown constructor!");
            }

            pets.add(pet);
            client.addPet(pet);

            return "New pet is successfully added! id = " + pet.getId();
        }
    }

    /**
     * Операция удаления клиента
     */
    private class RemoveClient implements Operation {
        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 2) throw new IncorrectDataEntryException();

            int clientId = Clinic.this.parseInt(words[1]);
            Client client = Clinic.this.getClient(clientId);
            clients.remove(client);

            return "Client is successfully removed!";
        }
    }

    /**
     * Операция удаления питомца
     */
    private class RemovePet implements Operation {
        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 2) throw new IncorrectDataEntryException();

            int petId = Clinic.this.parseInt(words[1]);
            Pet pet = Clinic.this.getPet(petId);

            if (pet != null && pet.getOwner() != null) pet.getOwner().removePet(pet);
            pets.remove(pet);

            return "Pet is successfully removed!";
        }
    }

    /**
     * Операция поиска клиента питомца
     */
    private class FindClientByPetName implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 2) throw new IncorrectDataEntryException();

            String clientName = "nobody";
            int petId = Clinic.this.parseInt(words[1]);
            Pet pet = Clinic.this.getPet(petId);
            if (pet.getOwner() != null) clientName = pet.getOwner().getName();

            return clientName;
        }
    }

    /**
     * Опирация поиска хозяина питомца
     */
    private class FindPetsByClientName implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 2) throw new IncorrectDataEntryException();

            String petsNames = "nobody";
            int clientId = Clinic.this.parseInt(words[1]);
            Client client = Clinic.this.getClient(clientId);
            Set<Pet> clientPets = client.getPets();
            if (clientPets.size() != 0) {
                petsNames = "";
                for (Pet pet : clientPets) {
                    petsNames += (pet.getName() + "\n");
                }
            }

            return petsNames;
        }
    }

    /**
     * Операция смены имени клиента
     */
    private class ChangeClientName implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 3) throw new IncorrectDataEntryException();

            int clientId = Clinic.this.parseInt(words[1]);
            Client client = Clinic.this.getClient(clientId);
            client.setName(words[2]);

            return "Name successfully changed!";
        }
    }

    /**
     * Операция смены имени питомца
     */
    private class ChangePetName implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 3) throw new IncorrectDataEntryException();

            int petId = Clinic.this.parseInt(words[1]);
            Pet pet = Clinic.this.getPet(petId);
            pet.setName(words[2]);

            return "Name successfully changed!";
        }
    }

    /**
     * Операция вывода списока всех клиентов
     */
    private class PrintAllClients implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            String result = "";
            for (Client client : clients) result += client.toString() + "\n" + "\n";
            return result;
        }
    }

    /**
     * Операция вывода списока всех питомцев
     */
    private class PrintAllPets implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            String result = "";
            for (Pet pet : pets) result += pet.toString() + "\n" + "\n";
            return result;
        }
    }

    /**
     * Операция вывода параметров клиента
     */
    private  class PrintClient implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 2) throw new IncorrectDataEntryException();

            int clientId = Clinic.this.parseInt(words[1]);
            Client client = Clinic.this.getClient(clientId);

            return client.toString();
        }
    }

    /**
     * Операция вывода параметров питомца
     */
    private  class PrintPet implements Operation {

        /**
         * Выполняет операцию
         *
         * @param words Распарсенные слова операции
         * @return Сообщение о выполнении или какой-то текст
         */
        public String doOperation(String[] words) throws MyException {
            if (words.length != 2) throw new IncorrectDataEntryException();

            int petId = Clinic.this.parseInt(words[1]);
            Pet pet = Clinic.this.getPet(petId);

            return pet.toString();
        }
    }
}