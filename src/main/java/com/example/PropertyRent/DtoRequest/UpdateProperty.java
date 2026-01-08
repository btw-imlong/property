package com.example.PropertyRent.DtoRequest;
import lombok.Data;
import java.math.BigDecimal;

public class UpdateProperty {
	
	// UPDATE
	@Data
	public class UpdatePropertyRequest {
	    private String title;
	    private String description;
	    private String address;
	    private BigDecimal price;
	    private String electricityCost;
	    private String waterCost;
	}

}
