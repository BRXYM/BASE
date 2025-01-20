package adm.control.all.controller;

import adm.control.all.service.AdmService;
import adm.control.all.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/executeInsertSql")
    public Result executeInsertSql(@RequestBody String sql) {
        sql = removeQuotes(sql);
        return admService.executeInsertSql(sql);
    }

    @PostMapping("/executeDeleteSql")
    public Result executeDeleteSql(@RequestBody String sql) {
        sql = removeQuotes(sql);
        return admService.executeDeleteSql(sql);
    }

    @PostMapping("/executeUpdateSql")
    public Result executeUpdateSql(@RequestBody String sql) {
        sql = removeQuotes(sql);
        return admService.executeUpdateSql(sql);
    }

    @PostMapping("/executeSelectSql")
    public Result executeSelectSql(@RequestBody String sql) {
        sql = removeQuotes(sql);
        return admService.executeSelectSql(sql);
    }

    private String removeQuotes(String sql) {
        if (sql != null) {
            return sql.replaceAll("^\"|\"$", "").trim();
        }
        return sql;
    }
}
