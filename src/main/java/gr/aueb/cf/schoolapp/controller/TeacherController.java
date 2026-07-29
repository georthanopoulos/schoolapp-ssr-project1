package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp.dto.RegionReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.service.IRegionService;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.validator.TeacherInsertValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final ITeacherService teacherService;
    private final IRegionService regionService;
    private final TeacherInsertValidator teacherInsertValidator;

//    @Autowired
//    public TeacherController(ITeacherService teacherService, IRegionService regionService) {
//        this.teacherService = teacherService;
//        this.regionService = regionService;
//    }


    @GetMapping("/insert")
    public String getTeacherForm(Model model) {
        model.addAttribute("teacherInsertDTO", TeacherInsertDTO.empty());
//        model.addAttribute("regionsReadOnlyDTO", regions());
        return "teacher-insert";
    }

    @PostMapping("/insert")
    public String teacherInsert(@Valid @ModelAttribute("teacherInsertDTO") TeacherInsertDTO teacherInsertDTO,
                                BindingResult bindingResult, Model model,
                                RedirectAttributes redirectAttributes) {

        teacherInsertValidator.validate(teacherInsertDTO, bindingResult);               // Business Rules

        if (bindingResult.hasErrors()) {
//            model.addAttribute("regionsReadOnlyDTO", regions());
            return "teacher-insert";
        }


        try {
            // saves the teacher
            TeacherReadOnlyDTO teacherReadOnlyDTO = teacherService.saveTeacher(teacherInsertDTO);

            // returns a success page

            // PRG -- Post-Redirect-Get     --- http code 302 -> redirect. Then Browser will make a get call to teacher-success.
            redirectAttributes.addFlashAttribute("teacherReadOnlyDTO", teacherReadOnlyDTO);            // This is the best way in order to avoid the duplicate insert by pressing F5
            return "redirect:/teachers/success";              // Controller

        } catch (EntityAlreadyExistsException | EntityInvalidArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());                             //error message -> see HTML. It will get the message from e.getMessage().
            return "teacher-insert";
        }
    }


    @GetMapping("/success")
    public String teacherInsertSuccess(Model model) {
        if (!model.containsAttribute("teacherReadOnlyDTO")) {             // Controls F5 - refresh
            return "redirect:/teachers";
        }
        return "teacher-success";
    }


    @ModelAttribute("regionsReadOnlyDTO")                                // Executed before every request handler (GET).
    public List<RegionReadOnlyDTO> regions() {
        return regionService.findAllRegionsSortedByName();

//        return List.of(
//                new RegionReadOnlyDTO(1L, "Αθήνα"),
//                new RegionReadOnlyDTO(2L, "Βόλος"),
//                new RegionReadOnlyDTO(3L, "Θεσσαλονίκη")
//        );
    }


}