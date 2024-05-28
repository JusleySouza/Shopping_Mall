package com.shopping.mall.storeapi.model.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {
	
	private UUID idUser;
	@JsonProperty("orderDetails")
	private List<RequestOrderDetailsDTO> orderDetails;

}
