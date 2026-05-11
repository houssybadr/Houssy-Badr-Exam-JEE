package ma.houssybadr.assurance.dtos;

public record ClientResponse(
        Long id,
        String nom,
        String email,
        long nombreContrats
) {}
