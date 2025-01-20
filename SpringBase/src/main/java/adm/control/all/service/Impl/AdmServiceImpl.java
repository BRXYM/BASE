package adm.control.all.service.Impl;

import adm.control.all.mapper.AdmMapper;
import adm.control.all.service.AdmService;
import adm.control.all.utils.Result;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class AdmServiceImpl implements AdmService {

    private static final Logger logger = LoggerFactory.getLogger(AdmServiceImpl.class);

    @Resource
    private AdmMapper admMapper;

    @Value("${LOCALDB}")
    private String localDB;

    private static final Pattern SQL_PATTERN = Pattern.compile(
        "^\\s*(SELECT|INSERT|UPDATE|DELETE)\\b", Pattern.CASE_INSENSITIVE
    );

    private boolean isValidSql(String sql) {
        Matcher matcher = SQL_PATTERN.matcher(sql);
        return matcher.find();
    }

    @Override
    public Result getTableInfo() {
        return Result.success(admMapper.getTableInfo(localDB), "获取成功");
    }

    @Override
    public Result executeInsertSql(String sql) {
        if (!isValidSql(sql)) {
            return Result.fail("无效的SQL语句");
        }
        try {
            int affectedRows = admMapper.executeInsertSql(sql);
            logger.info("INSERT SQL: {}, Affected Rows: {}", sql, affectedRows);
            return Result.success(affectedRows, "INSERT执行成功");
        } catch (Exception e) {
            logger.error("INSERT SQL执行失败: {}", sql, e);
            return Result.fail("INSERT执行失败: " + e.getMessage());
        }
    }

    @Override
    public Result executeDeleteSql(String sql) {
        if (!isValidSql(sql)) {
            return Result.fail("无效的SQL语句");
        }
        try {
            int affectedRows = admMapper.executeDeleteSql(sql);
            logger.info("DELETE SQL: {}, Affected Rows: {}", sql, affectedRows);
            return Result.success(affectedRows, "DELETE执行成功");
        } catch (Exception e) {
            logger.error("DELETE SQL执行失败: {}", sql, e);
            return Result.fail("DELETE执行失败: " + e.getMessage());
        }
    }

    @Override
    public Result executeUpdateSql(String sql) {
        if (!isValidSql(sql)) {
            return Result.fail("无效的SQL语句");
        }
        try {
            int affectedRows = admMapper.executeUpdateSql(sql);
            logger.info("UPDATE SQL: {}, Affected Rows: {}", sql, affectedRows);
            return Result.success(affectedRows, "UPDATE执行成功");
        } catch (Exception e) {
            logger.error("UPDATE SQL执行失败: {}", sql, e);
            return Result.fail("UPDATE执行失败: " + e.getMessage());
        }
    }

    @Override
    public Result executeSelectSql(String sql) {
        if (!isValidSql(sql)) {
            return Result.fail("无效的SQL语句");
        }
        try {
            List<Map<String, Object>> result = admMapper.executeSelectSql(sql);
            logger.info("SELECT SQL: {}, Result: {}", sql, result);
            return Result.success(result, "SELECT执行成功");
        } catch (Exception e) {
            logger.error("SELECT SQL执行失败: {}", sql, e);
            return Result.fail("SELECT执行失败: " + e.getMessage());
        }
    }
}
