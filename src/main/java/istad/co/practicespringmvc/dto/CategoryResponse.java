package istad.co.practicespringmvc.dto;
import lombok.Builder;
@Builder
public record CategoryResponse(
        int id,
        String title,
        String description
        ) {
}
