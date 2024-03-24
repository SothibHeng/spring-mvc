package istad.co.practicespringmvc.dto;
import lombok.Builder;
@Builder
public record CategoryRequest(
        String title ,
        String description
) {

}
