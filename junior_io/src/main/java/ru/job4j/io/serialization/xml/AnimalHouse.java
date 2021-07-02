package ru.job4j.io.serialization.xml;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "animalHouse")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalHouse {
    @XmlAttribute
    private String name;
    private boolean open;
    private int capacity;

    @XmlElementWrapper(name = "animals")
    @XmlElement(name = "animal")
    private Animal[] animals;

    public AnimalHouse() {
    }

    public AnimalHouse(String name, Animal... animals) {
        this.open = true;
        this.capacity = animals.length;
        this.name = name;
        this.animals = animals;
    }

    public Animal getFirst() {
        return animals[0];
    }

    public boolean isOpen() {
        return open;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AnimalHouse{"
                + "name='" + name + '\''
                + ", open=" + open
                + ", capacity=" + capacity
                + ", animals=" + Arrays.toString(animals)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final AnimalHouse animalHouse = new AnimalHouse(
                "Home1", new Animal("Jerry"), new Animal("Tom"));

        JAXBContext context = JAXBContext.newInstance(AnimalHouse.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(animalHouse, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


