package ru.vetclinic.clinic.Main;

import ru.vetclinic.clinic.Exceptions.*;
import ru.vetclinic.clinic.Pets.Cat;
import ru.vetclinic.clinic.Pets.Dog;
import ru.vetclinic.clinic.Pets.Pet;
import ru.vetclinic.clinic.Pets.Turtle;

import java.util.*;

/**
 * Ветеренарная клиника
 */
public class Clinic {
    /** Список клиентов клиники */
    private final Set<Client> clients = new HashSet<Client>();
    /** Список питомцев в клинике */
    private final Set<Pet> pets = new HashSet<Pet>();

    /** Список всех доступных операций */
    private final HashMap<String, Operation> availableOperations = new HashMap<String, Operation>();
    /** Список всех доступных видом животных */
    private final HashMap<String, Class> availablePets = new HashMap<String, Class>();

    /** Конструктор */
    public Clinic() {
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
     * @param s Текст запроса
     * @return Текс или сообщение
     * @throws MyException Различные исключения
     */
    public String doOperation(String s) throws MyException {
        String message = "";
        String[] words = s.split(" ");
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
     * Возвращает клиента по имени
     * @param name Имя клиента
     * @return Клиента или null, если такого клиента нету
     */
    public Client getClient(String name) throws ClientDoesntExistException {
        Client result = null;
        for (Client client : clients) {
            if (client.getName().equals(name)) {
                result = client;
                break;
            }
        }
        if (result == null) throw new ClientDoesntExistException();
        return result;
    }

    /**
     * Возвращает питомца по имени
     * @param name Имя питомца
     * @return Клиента или null, если такого клиента нету
     */
    public Pet getPet(String name) throws PetDoesntExistException {
        Pet result = null;
        for (Pet pet : pets) {
            if (pet.getName().equals(name)) {
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

    /** Геттеры */
    public Set<Client> getClients() {
        return clients;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    /////////////////////////////////////////////////////////////////////////////////////

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

            Client client = new Client(name);

            if (Clinic.this.clients.contains(client)) throw new ThisClientNameAlreadyExistsException();

            clients.add(client);
            clients.notifyAll();

            return "New client is successfully added!";
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
                client = Clinic.this.getClient(words[1]);
            }
            clients.notifyAll();


            String petType = words[2];
            if (!availablePets.containsKey(petType)) throw new UnknownPetTypeException();

            String petName = words[3];

            Pet pet = null;
            try {
                pet = (Pet) availablePets.get(petType).getConstructor(Client.class, String.class).newInstance(client, petName);
            }
            catch (Exception e) {
                System.out.println("Unknown constructor!");
            }


            if (Clinic.this.pets.contains(pet)) throw new ThisPetNameAlreadyExistsException();
            Clinic.this.pets.add(pet);
            pets.notifyAll();

            if (client != null) client.addPet(pet);
            clients.notifyAll();

            return "New pet is successfully added!";
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

            String name = words[1];
            Client client = Clinic.this.getClient(name);

            for (Pet pet : pets) {
                pet.setOwner(null);
            }

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

            Pet pet = Clinic.this.getPet(words[1]);

            if (pet != null && pet.getOwner() != null) pet.getOwner().removePet(pet);
            pets.remove(pet);

            return "Pet is successfully removed!";
        }
    }

    /**
     * Операция поиска хозяина питомца
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
            Pet pet = Clinic.this.getPet(words[1]);
            if (pet.getOwner() != null) clientName = pet.getOwner().getName();

            return clientName;
        }
    }

    /**
     * Опирация поиска питосцев клиента
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
            Client client = Clinic.this.getClient(words[1]);
            Set<Pet> clientPets = client.getPets();
            if (clientPets.size() != 0) {
                petsNames = "";
                for (Pet pet : clientPets) {
                    petsNames += (pet.getPetType() + " " + pet.getName() + "\n");
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

            Client client = Clinic.this.getClient(words[1]);
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

            Pet pet = Clinic.this.getPet(words[1]);
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
            for (Pet pet : pets) result += pet.toString() + "\n";
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

            Client client = Clinic.this.getClient(words[1]);

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

            Pet pet = Clinic.this.getPet(words[1]);

            return pet.toString();
        }
    }
}