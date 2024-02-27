package suai.labs.spring.transport.service.transport;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import suai.labs.spring.transport.service.JpaEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "transport")
@NoArgsConstructor
public class Transport extends JpaEntity {

	@NotBlank
	private String carName;

	@NotBlank
	private String number;

	@NotBlank
	private String year;

	@NotBlank
	private String price;

	@NotBlank
	private String stateTransport;

	@NotBlank
	private String availability;

	public Transport(String carName, String number, String year, String price,
					 String stateTransport, String availability) {
		this.carName = carName;
		this.number = number;
		this.year = year;
		this.price = price;
		this.stateTransport = stateTransport;
		this.availability = availability;
	}

}
