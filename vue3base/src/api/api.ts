import axios from 'axios';
import type { User, Ord, Cla, Good, Sela, Teach } from '@/type/type';

const API_URL = '/api';

// User API
/**
 * 添加用户
 * @param user 用户信息
 */
export const addUser = async (user: Partial<User>) => {
    return axios.post(`${API_URL}/user/addUser`, user);
};

/**
 * 删除用户
 * @param userId 用户ID
 */
export const deleteUser = async (userId: number) => {
    return axios.post(`${API_URL}/user/deleteUser`, { uid: userId });
};

/**
 * 获取所有用户
 */
export const getAllUsers = async () => {
    return axios.get(`${API_URL}/user/getAllUsers`);
};

/**
 * 用户登录
 * @param user 用户信息
 */
export const loginUser = async (user: Partial<User>) => {
    return axios.post(`${API_URL}/user/login`, user);
};

/**
 * 更新用户信息
 * @param user 用户信息
 */
export const updateUser = async (user: Partial<User>) => {
    return axios.post(`${API_URL}/user/updateUser`, user);
};

// Teach API
/**
 * 教师登录
 * @param teach 教师信息
 */
export const loginTeach = async (teach: Partial<Teach>) => {
    return axios.post(`${API_URL}/teach/login`, teach);
};
/**
 * 添加教学信息
 * @param teach 教学信息
 */
export const addTeach = async (teach: Partial<Teach>) => {
    return axios.post(`${API_URL}/teach/addTeach`, teach);
};

/**
 * 删除教学信息
 * @param teachId 教学ID
 */
export const deleteTeach = async (teachId: number) => {
    return axios.post(`${API_URL}/teach/deleteTeach`, { teachId });
};

/**
 * 获取所有教学信息
 */
export const getAllTeaches = async () => {
    return axios.get(`${API_URL}/teach/getAllTeaches`);
};

/**
 * 根据ID获取教学信息
 * @param teachId 教学ID
 */
export const getTeachById = async (teachId: number) => {
    return axios.get(`${API_URL}/teach/getTeachById`, { params: { teachId } });
};

/**
 * 更新教学信息
 * @param teach 教学信息
 */
export const updateTeach = async (teach: Partial<Teach>) => {
    return axios.post(`${API_URL}/teach/updateTeach`, teach);
};

// Good API
/**
 * 添加商品信息
 * @param good 商品信息
 */
export const addGood = async (good: Partial<Good>) => {
    return axios.post(`${API_URL}/good/addGood`, good);
};

/**
 * 删除商品信息
 * @param goodId 商品ID
 */
export const deleteGood = async (goodId: number) => {
    return axios.post(`${API_URL}/good/deleteGood`, { goodId });
};

/**
 * 获取所有商品信息
 */
export const getAllGoods = async () => {
    return axios.get(`${API_URL}/good/getAllGoods`);
};

/**
 * 根据ID获取商品信息
 * @param goodId 商品ID
 */
export const getGoodById = async (goodId: number) => {
    return axios.get(`${API_URL}/good/getGoodById`, { params: { goodId } });
};

/**
 * 更新商品信息
 * @param good 商品信息
 */
export const updateGood = async (good: Partial<Good>) => {
    return axios.post(`${API_URL}/good/updateGood`, good);
};

// Sela API
/**
 * 添加选课信息
 * @param sela 选课信息
 */
export const addSela = async (sela: Partial<Sela>) => {
    return axios.post(`${API_URL}/sela/addSela`, sela);
};

/**
 * 删除选课信息
 * @param selaId 选课ID
 */
export const deleteSela = async (selaId: number) => {
    return axios.post(`${API_URL}/sela/deleteSela`, { selaId });
};

/**
 * 获取所有选课信息
 */
export const getAllSelas = async () => {
    return axios.get(`${API_URL}/sela/getAllSelas`);
};

/**
 * 根据ID获取选课信息
 * @param selaId 选课ID
 */
export const getSelaById = async (selaId: number) => {
    return axios.get(`${API_URL}/sela/getSelaById`, { params: { selaId } });
};

/**
 * 更新选课信息
 * @param sela 选课信息
 */
export const updateSela = async (sela: Partial<Sela>) => {
    return axios.post(`${API_URL}/sela/updateSela`, sela);
};

// Cla API
/**
 * 添加课程信息
 * @param cla 课程信息
 */
export const addCla = async (cla: Partial<Cla>) => {
    return axios.post(`${API_URL}/cla/addCla`, cla);
};

/**
 * 删除课程信息
 * @param claId 课程ID
 */
export const deleteCla = async (claId: number) => {
    return axios.get(`${API_URL}/cla/deleteCla`, { params: { claId} });
};

/**
 * 获取所有课程信息
 */
export const getAllClas = async () => {
    return axios.get(`${API_URL}/cla/getAllClas`);
};

/**
 * 根据ID获取课程信息
 * @param claId 课程ID
 */
export const getClaById = async (claId: number) => {
    return axios.get(`${API_URL}/cla/getClaById`, { params: { claId } });
};

/**
 * 更新课程信息
 * @param cla 课程信息
 */
export const updateCla = async (cla: Partial<Cla>) => {
    return axios.post(`${API_URL}/cla/updateCla`, cla);
};

/**
 * 根据教师ID获取课程信息
 * @param teacherId 教师ID
 */
export const getClasByTeacherId = async (teacherId: number) => {
    return axios.get(`${API_URL}/cla/getClasByTeacherId`, { params: { teacherId } });
};

// Ord API
/**
 * 添加订单信息
 * @param ord 订单信息
 */
export const addOrd = async (ord: Partial<Ord>) => {
    return axios.post(`${API_URL}/ord/addOrd`, ord);
};

/**
 * 删除订单信息
 * @param ordId 订单ID
 */
export const deleteOrd = async (ordId: number) => {
    return axios.post(`${API_URL}/ord/deleteOrd`, { ordId });
};

/**
 * 获取所有订单信息
 */
export const getAllOrds = async () => {
    return axios.get(`${API_URL}/ord/getAllOrds`);
};
/**
 * 根据id获取订单信息
 */
export const getOrdByUserId = async (userId: number) => {
    return axios.get(`${API_URL}/ord/getOrdsByUserId`, { params: { userId } });
};

/**
 * 根据ID获取订单信息
 * @param ordId 订单ID
 */
export const getOrdById = async (ordId: number) => {
    return axios.get(`${API_URL}/ord/getOrdById`, { params: { ordId } });
};

/**
 * 更新订单信息
 * @param ord 订单信息
 */
export const updateOrd = async (ord: Partial<Ord>) => {
    return axios.post(`${API_URL}/ord/updateOrd`, ord);
};