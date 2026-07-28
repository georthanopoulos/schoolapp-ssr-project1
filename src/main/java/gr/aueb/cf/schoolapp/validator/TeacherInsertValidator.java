package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherInsertValidator implements Validator {

    private final TeacherService teacherService;

    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherInsertDTO.class == clazz;              // clazz = buzz word due to the fact that class is meaningful in JAVA
    }

    @Override
    public void validate(Object target, Errors errors) {                   // Binding result is sub-interface of Errors.
        TeacherInsertDTO teacherInsertDTO = (TeacherInsertDTO) target;

        if (teacherInsertDTO.vat() != null &&
                teacherService.isTeacherExistsByVat(teacherInsertDTO.vat())) {
            log.info ("Validation failed. Teacher with VAT={} already exists", teacherInsertDTO.vat());
            errors.rejectValue("vat", "vat.teacher.exists");           // TODO localization
        }
    }
}
