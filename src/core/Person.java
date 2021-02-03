package core;

public class Person implements Comparable {

	private long ssn;
	private String firstName;
	private String lastName;
	private String city;
	private String country;
	private long income;

	@Override
	public String toString() {
		return "Person [ssn=" + ssn + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city
				+ ", country=" + country + ", income=" + income + "]";
	}

	public Person(long ssn, String firstName, String lastName, String city, String country, long income) {
		super();
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.country = country;
		this.income = income;
	}

	public long getSsn() {
		return ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public long getIncome() {
		return income;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Person) {
			Person p = (Person) o;
			return (this.firstName.compareTo(p.firstName));
		} else
			return -1;
	}

}
