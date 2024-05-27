package com.shopping.mall.productsapi.model.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
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
public class RequestProductDTO {
	
	@NotEmpty(message = "{name.not.empty}")
	@Size(min = 2, max = 45, message = "{name.size}")
	@ApiModelProperty(notes = "Product name", example = "Televisão", required = true)
	private String name;
	@NotEmpty(message = "{description.not.empty}")
	@Size(min = 15, max = 255, message = "{description.size}")
	@ApiModelProperty(notes = "Product description", example = "Produto novo, completo", required = true)
	private String description;
	@NotNull(message = "{sku.not.null}")
	@ApiModelProperty(notes = "Product sku", example = "875", required = true)
	private int sku;
	@NotNull(message = "{valueUnitary.not.null}")
	@Min(value=0, message = "{valueUnitary.not.less.than}")
	@ApiModelProperty(notes = "Product valueUnitary", example = "1030.65", required = true)
	private Double valueUnitary;
	@NotNull(message = "{stock.not.null}")
	@Min(value=0, message = "{stock.not.less.than}")
	@ApiModelProperty(notes = "Product stock", example = "7", required = true)
	private int stock;
	@ApiModelProperty(notes = "Product idSubCategory", example = "03db7eed-e889-410c-ab70-1769b667ff4f", required = false)
	private UUID idSubCategory;
	@NotNull(message = "{category.not.null}")
	@ApiModelProperty(notes = "Product idCategory", example = "03db7eed-e889-410c-ab70-1769b667ff4f", required = true)
	private UUID idCategory;

}
