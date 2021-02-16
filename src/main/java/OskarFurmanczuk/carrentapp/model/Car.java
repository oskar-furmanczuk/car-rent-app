package OskarFurmanczuk.carrentapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "brand")
	@NotBlank(message = "Brand name cannot be blank")
	@Pattern(regexp="^[A-Za-z]*$", message = "Brand name must contain only letters (ASCII encoding only)")
	@Size(min=2, max=10, message = "Brand name must be from 2 to 10 characters long")
	private String brand;
	
	@Column(name = "model")
	@NotBlank(message = "Model name cannot be blank")
	@Size(min=2, max=10, message = "Model name must be from 2 to 10 characters long")
	private String model;
	
	@Column(name = "license_plate")
	@NotBlank(message = "License plate cannot be blank")
	@Size(min=4, max=10, message = "License plate must be from 4 to 10 characters long")
	private String licesnsePlate;
	

	@OneToOne(mappedBy = "car")
    private Client client;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicesnsePlate() {
		return licesnsePlate;
	}

	public void setLicesnsePlate(String licesnsePlate) {
		this.licesnsePlate = licesnsePlate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
