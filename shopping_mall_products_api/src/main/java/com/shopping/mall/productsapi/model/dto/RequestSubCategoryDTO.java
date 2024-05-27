package com.shopping.mall.productsapi.model.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class RequestSubCategoryDTO {
	
	@NotEmpty(message = "{nameSubCategory.not.empty}")
	@Size(min = 3, max = 45, message = "{nameSubCategory.size}")
	@ApiModelProperty(notes = "SubCategory name", example = "Televisores", required = true)
	private String nameSubCategory;
	@NotEmpty(message = "{descriptionSubCategory.not.empty}")
	@Size(min = 15, max = 255, message = "{descriptionSubCategory.size}")
	@ApiModelProperty(notes = "SubCategory description", example = "Super economicos e portateis", required = true)
	private String description;
	@NotNull(message = "{category.not.null}")
	@ApiModelProperty(notes = "Id category", example = "d834d4b3-e485-419e-a701-159b0c908799", required = true)
	private UUID idCategory;

}
