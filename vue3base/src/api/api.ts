import axios from 'axios';

const API_BASE_URL = '/api';

export interface Result {
  statusCode: number;
  code: string;
  list: object;
  message: string;
}

// POST admController/executeDeleteSql
/**
 * 执行删除 SQL 语句
 * @param sql - 要执行的 SQL 语句
 * @returns 返回操作结果
 */
export const executeDeleteSql = async (sql: string): Promise<Result> => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admController/executeDeleteSql`, sql, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(`执行删除 SQL 时出错: ${error}`);
  }
};

// POST admController/executeInsertSql
/**
 * 执行插入 SQL 语句
 * @param sql - 要执行的 SQL 语句
 * @returns 返回操作结果
 */
export const executeInsertSql = async (sql: string): Promise<Result> => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admController/executeInsertSql`, sql, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(`执行插入 SQL 时出错: ${error}`);
  }
};

// POST admController/executeSelectSql
/**
 * 执行查询 SQL 语句
 * @param sql - 要执行的 SQL 语句
 * @returns 返回操作结果
 */
export const executeSelectSql = async (sql: string): Promise<Result> => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admController/executeSelectSql`, sql, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(`执行查询 SQL 时出错: ${error}`);
  }
};

// POST admController/executeUpdateSql
/**
 * 执行更新 SQL 语句
 * @param sql - 要执行的 SQL 语句
 * @returns 返回操作结果
 */
export const executeUpdateSql = async (sql: string): Promise<Result> => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admController/executeUpdateSql`, sql, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(`执行更新 SQL 时出错: ${error}`);
  }
};

// GET admController/getTableInfo
/**
 * 获取表信息
 * @returns 返回表信息的结果
 */
export const getTableInfo = async (): Promise<Result> => {
  try {
    const response = await axios.get(`${API_BASE_URL}/admController/getTableInfo`);
    return response.data;
  } catch (error) {
    throw new Error(`获取表信息时出错: ${error}`);
  }
};
