package OskarFurmanczuk.carrentapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	@NotBlank(message = "First name cannot be blank")
	@Size(min=3, max=10, message = "First name must be from 3 to 10 characters long")
	@Pattern(regexp="^[A-Za-z]*$", message = "First name must contain only letters (ASCII encoding only)")
	String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "Last name cannot be blank")
	@Pattern(regexp="^[A-Za-z]*$",message = "Last name must contain only letters (ASCII encoding only)")
	@Size(min=3, max=10, message = "Last name must be from 3 to 10 characters long")
	private String lastName;
	
	
	@Column(name = "phone_number")
	@NotBlank(message = "Phone number cannot be blank")
	@Pattern(regexp="^[\\d]*$", message = "Phone number must contain only digits")
	@Size(min=9, max=13, message = "Phone number should be 9 to 13 digits long")
	private String phoneNumber;
	
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}


}
