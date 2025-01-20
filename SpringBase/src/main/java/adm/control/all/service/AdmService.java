package adm.control.all.service;

import adm.control.all.utils.Result;

public interface AdmService {
    Result getTableInfo();
    Result executeInsertSql(String sql);
    Result executeDeleteSql(String sql);
    Result executeUpdateSql(String sql);
    Result executeSelectSql(String sql);
}
