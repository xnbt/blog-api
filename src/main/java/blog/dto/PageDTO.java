package blog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageDTO {
    private Long totalElements;

    private Integer totalPages;

    private List<? extends  BaseDTO> elements;

}
