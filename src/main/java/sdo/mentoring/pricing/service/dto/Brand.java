package sdo.mentoring.pricing.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Brand implements Serializable {
    private Integer brandId;
    private String name;
}


