package istad.co.practicespringmvc.dto;
import lombok.Builder;
@Builder
public record ProductResponse(
        int id ,
        String title ,
        String description,
        float price,
        String imageUrl
) {
}
