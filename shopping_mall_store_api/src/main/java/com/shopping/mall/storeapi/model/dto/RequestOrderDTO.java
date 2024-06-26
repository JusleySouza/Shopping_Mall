package com.shopping.mall.storeapi.model.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty(notes = "Id user", example = "000acde0-4d72-4049-9705-0eb5ce87e2fa", required = true)
	private UUID idUser;
	@JsonProperty("orderDetails")
	private List<RequestOrderDetailsDTO> orderDetails;

}
