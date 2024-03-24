package istad.co.practicespringmvc.dto;

public record ProductRequest
        (
        String title,
        String description,
        float price,
        String imageUrl
) {
}
