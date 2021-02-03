package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person(1, "Bob", "Haris", "Tokyo", "Japan", 100000));
		persons.add(new Person(2, "Sebastian", "Wilder", "LA", "USA", 100));
		persons.add(new Person(3, "Mia", "Dolan", "LA", "USA", 100));
		persons.add(new Person(4, "Andrew", "Neiman", "NYC", "USA", 0));
		persons.add(new Person(5, "Terence", "Fletcher", "NYC", "USA", 10000));
		persons.add(new Person(6, "Forrest", "Gump", "Savannah", "USA", 100));
		persons.add(new Person(7, "Tyler", "Durden", null, "USA", 1000));
		persons.add(new Person(8, "Vito", "Corleone", "NYC", "USA", 1000000));
		persons.add(new Person(9, "Luke", "Skywalker", null, null, 0));
		persons.add(new Person(10, "Norman", "Bates", null, "USA", 10));

		// check if all elements of stream match the criteria
		boolean allPersonsHaveFirstName = persons.stream().allMatch(p -> p.getFirstName() != null);
		System.out.println("All elements in list have first name? " + allPersonsHaveFirstName);

		// check if at least of the elements of stream match the criteria
		boolean allPersonsHaveCounntry = persons.stream().anyMatch(p -> p.getCountry() != null);
		System.out.println("Atleast one element in list has country name? " + allPersonsHaveCounntry);

		boolean noneMatch = persons.stream().noneMatch(p -> p.getSsn() == 0);

		// filter stream, find first
		Person person = persons.stream().filter(p -> p.getIncome() > 10000).findFirst().get();

		Person anyPerson = persons.stream().filter(p -> p.getIncome() > 10000).findAny().get();

		// there's also mapToInt, mapToDouble and mapToLong
		long distinctCountryCount = persons.stream().map(p -> p.getCountry()).distinct().count();

		List<Person> skippedLimitedPersons = persons.stream().skip(7).limit(2).collect(Collectors.toList());
		System.out.println("8th and 9th element in list " + skippedLimitedPersons);

		// map of ssn vs person
		// identity function in lieu of p->p
		Map<Long, Person> idPersonMap = persons.stream().collect(Collectors.toMap(Person::getSsn, Function.identity()));
		System.out.println("Map of ssn vs person " + idPersonMap);

		// group persons by their city
		// since some city fields are null, they are filtered out
		// other wise grouping function will throw NPE
		Map<String, List<Person>> personsGroupedByCity = persons.stream().filter(p -> p.getCity() != null)
				.collect(Collectors.groupingBy(Person::getCity));
		System.out.println("Map of persons grouped by city " + personsGroupedByCity);

		// get person with max income
		Person richPerson = persons.stream().max(Comparator.comparing(Person::getIncome)).get();
		System.out.println("Person with most income " + richPerson);

		Person poorPerson = persons.stream().min(Comparator.comparing(Person::getIncome)).get();
		System.out.println("Person with least income " + poorPerson);

		// print elements
		persons.stream().forEach(System.out::println);

		// sort by natural order
		// Class should override compareTo()
		List<Person> naturalSortedList = persons.stream().sorted().collect(Collectors.toList());
		System.out.println("Persons sorted by first name " + naturalSortedList);

		// sort by custom order
		List<Person> sortedList = persons.stream().sorted(Comparator.comparingLong(Person::getIncome))
				.collect(Collectors.toList());
		System.out.println("Persons sorted by income " + sortedList);

		// sort by custom order
		List<Person> sortedListDescending = persons.stream()
				.sorted(Comparator.comparingLong(Person::getIncome).reversed()).collect(Collectors.toList());
		System.out.println("Persons sorted by income in descending order " + sortedListDescending);

		// peek allows to perform some action on each element of list AND return a
		// stream
		List<Person> personsWith2XIncome = persons.stream().peek(p -> p.setIncome(p.getIncome() * 2))
				.collect(Collectors.toList());
		System.out.println("persons with doubled income " + personsWith2XIncome);

		// create map of map
		Map<String, Map<String, List<Person>>> collect = persons.stream()
				.filter(p -> p.getCity() != null && p.getCountry() != null)
				.collect(Collectors.groupingBy(Person::getCountry, Collectors.groupingBy(Person::getCity)));

	}

}
