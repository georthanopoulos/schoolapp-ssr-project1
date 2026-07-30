package gr.aueb.cf.schoolapp.dto;

import java.util.UUID;

public record TeacherEditReadOnlyDTO(

        UUID uuid,

        String firstname,

        String lastname,

        String vat,

        Long regionId

) {
}