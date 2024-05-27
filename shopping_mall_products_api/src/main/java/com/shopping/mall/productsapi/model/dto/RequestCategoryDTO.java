package com.shopping.mall.productsapi.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class RequestCategoryDTO {
	
	@NotEmpty(message = "{nameCategory.not.empty}")
	@Size(min = 3, max = 45, message = "{nameCategory.size}")
	@ApiModelProperty(notes = "Category name", example = "Eletro Portáteis", required = true)
	private String nameCategory;

}
