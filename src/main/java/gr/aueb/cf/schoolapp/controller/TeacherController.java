package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dto.RegionReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

//    private final ITeacherService teacherService;
//    private final IRegionService regionService;

    @GetMapping("/insert")
    public String getTeacherForm(Model model) {
        model.addAttribute("teacherInsertDTO", TeacherInsertDTO.empty());
//        model.addAttribute("regionsReadOnlyDTO", regions());
        return "techer-insert";
    }



    @ModelAttribute("regionsReadOnlyDTO")                       // Executed prior to any request (GET) handler
    public List<RegionReadOnlyDTO> regions() {
//      return regionService.findAllRegionsSortedByName();
    }

    return List. of(
            new RegionReadOnlyDTO(1L, "Αθήνα"),
            new RegionReadOnlyDTO(1L, "Βόλος"),
            new RegionReadOnlyDTO(1L, "Θεσσαλονίκη")
    );


}
