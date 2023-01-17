import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> recruit = persons.stream()
                .filter(x -> x.getAge() > 17 && x.getAge() < 28)
                .filter(x -> x.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());

//        System.out.println(recruit);  // для проверки с небольшим населением в 100

        List<Person> workers = persons.stream()
                .filter(x -> x.getAge() > 17)
                .filter(x -> !(x.getAge() > 65 && x.getSex() == Sex.MAN))
                .filter(x -> !(x.getAge() > 60 && x.getSex() == Sex.WOMAN))
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

//        for (Person worker : workers) {   // для проверки с небольшим населением в 100
//            System.out.println(worker);
//        }

//        for (Person person : persons) {   // для проверки работы фильтов
//            System.out.println(person);
//        }
    }
}
