package adm.control.all.controller;


import adm.control.all.service.AdmService;
import adm.control.all.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/admController")
public class AdmController {

    @Resource
    private AdmService admService;

    @GetMapping("/getTableInfo")
    public Result getTableInfo() {
        return admService.getTableInfo();
    }
}
