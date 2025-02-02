import {defineStore} from "pinia";
import {
    addUser, deleteUser, getAllUsers, loginUser, updateUser,
    addTeach, deleteTeach, getAllTeaches, getTeachById, updateTeach,
    addGood, deleteGood, getAllGoods, getGoodById, updateGood,
    addOrd, deleteOrd, getAllOrds, getOrdById, updateOrd, getOrdByUserId,
    addCla, deleteCla, getAllClas, getClaById, updateCla, getClasByTeacherId,
    addSela, deleteSela, getAllSelas, getSelaById, updateSela, loginTeach
} from '@/api/api';
import type {User, Ord, Cla, Good, Sela, Teach} from '@/type/type';
import {ElMessage} from 'element-plus';
import {ref} from "vue"

// User Store
export const useUserStore = defineStore('user', {
    state: () => ({
        users: [] as User[] | null,
        currentUser: null as User | null,
        islogin: 0, // 新增 islogin 状态
    }),
    actions: {
        /**
         * 获取所有用户
         */
        async fetchUsers() {
            const {data: {statusCode, code, list, message}} = await getAllUsers();
            if (statusCode === 200) {
                this.users = list as User[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 添加新用户
         * @param user - 用户对象
         */
        async regist(user: User) {
            const {data: {statusCode, code, list, message}} = await addUser(user);
            if (statusCode === 200) {
                ElMessage.success("注册成功");
                this.islogin = 2;
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 删除指定ID的用户
         * @param id - 用户ID
         */
        async deleteUser(id: number) {
            const {data: {statusCode, code, list, message}} = await deleteUser(id);
            if (statusCode === 200) {
                await this.fetchUsers();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 用户登录
         * @param userForm - 用户登录表单
         */
        async login(userForm: User) {
            const {data: {statusCode, code, list, message}} = await loginUser(userForm);
            if (statusCode === 200) {
                this.currentUser = list; // 修改: 将 currentUser 赋值为 User 类型
                this.islogin = 1; // 设置登录状态为 1 0 未登录 1登录 2登录页面 3注册页面
                ElMessage.success(message);
            } else {
                ElMessage.error(message); // 添加: 登录失败时弹出错误提示
            }
        },

        /**
         * 更新用户信息
         * @param user - 用户对象
         */
        async updateUser(user: User) {
            const {data: {statusCode, code, list, message}} = await updateUser(user);
            if (statusCode === 200) {
                await this.fetchUsers();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 设置用户为空
         */
        setUserNull() {
            this.currentUser = null;
            this.islogin = 0;
        },

        setIsLogin(value: number) {
            this.islogin = value;
        },
    }
});
// Teach Store
export const useTeachStore = defineStore('teach', {
    state: () => ({
        teaches: [] as Teach[] | null,
        currentTeach: null as any | null,
        isTeacherLogin: false,
    }),
    actions: {
        /**
         * 获取所有教练
         */
        async fetchTeaches() {
            const {data: {statusCode, code, list, message}} = await getAllTeaches();
            if (statusCode === 200) {
                this.teaches = list as Teach[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 添加新教练
         * @param teach - 教练对象
         */
        async addTeach(teach: Partial<Teach>) {
            const {data: {statusCode, code, list, message}} = await addTeach(teach);
            if (statusCode === 200) {
                ElMessage.success("添加成功");
                await this.fetchTeaches();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 删除指定ID的教练
         * @param id - 教练ID
         */
        async deleteTeach(id: number) {
            const {data: {statusCode, code, list, message}} = await deleteTeach(id);
            if (statusCode === 200) {
                await this.fetchTeaches();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 更新教练信息
         * @param teach - 教练对象
         */
        async updateTeach(teach: Partial<Teach>) {
            const {data: {statusCode, code, list, message}} = await updateTeach(teach);
            if (statusCode === 200) {
                await this.fetchTeaches();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 设置当前教练为空
         */
        setCurrentTeachNull() {
            this.currentTeach = null;
        },

        /**
         * 教师登录
         * @param teach - 教师信息
         */
        async teacherLogin(teach: Partial<Teach>) {
            const {data: {statusCode, code, list, message}} = await loginTeach(teach);
            if (statusCode === 200) {
                this.currentTeach = list;
                this.isTeacherLogin = true;
                ElMessage.success(message);
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 教师退出登录
         */
        logout() {
            this.currentTeach = null;
            this.isTeacherLogin = false;
        },
    }
});

// Good Store
export const useGoodStore = defineStore('good', {
    state: () => ({
        goods: [] as Good[] | null,
        currentGood: ref<Good>({
            good_id: null,
            good_name: null,
            good_img: null,
            good_txt: null,
            good_price: null,
            good_num: null,
        })
    }),
    actions: {
        /**
         * 获取所有商品
         */
        async fetchGoods() {
            const {data: {statusCode, code, list, message}} = await getAllGoods();
            if (statusCode === 200) {
                this.goods = list as Good[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 添加新商品
         * @param good - 商品对象
         */
        async addGood() {
            const {data: {statusCode, code, list, message}} = await addGood(this.currentGood);
            if (statusCode === 200) {
                this.setCurrentGoodNull()
                await this.fetchGoods();
                ElMessage.success("添加成功");
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 删除指定ID的商品
         * @param id - 商品ID
         */
        async deleteGood(id: number) {
            const {data: {statusCode, code, list, message}} = await deleteGood(id);
            if (statusCode === 200) {
                await this.fetchGoods();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 更新商品信息
         * @param good - 商品对象
         */
        async updateGood(good: Partial<Good>) {
            const {data: {statusCode, code, list, message}} = await updateGood(good);
            if (statusCode === 200) {
                await this.fetchGoods();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 设置当前商品为空
         */
        setCurrentGoodNull() {
            this.currentGood = {
                good_id: null,
                good_name: null,
                good_img: null,
                good_txt: null,
                good_price: null,
                good_num: null,
            };
        },
    }
});

// Ord Store
export const useOrdStore = defineStore('ord', {
    state: () => ({
        ords: [] as Ord[] | null,
        currentOrd: null as any | null,
    }),
    actions: {
        /**
         * 获取所有订单
         */
        async fetchOrds() {
            const {data: {statusCode, code, list, message}} = await getAllOrds();
            if (statusCode === 200) {
                this.ords = list as Ord[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 根据用户ID获取订单
         * @param userId - 用户ID
         */
        async getOrdByUserId(userId: number) {
            const {data: {statusCode, code, list, message}} = await getOrdByUserId(userId);
            if (statusCode === 200) {
                this.ords = list as Ord[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 添加新订单
         * @param ord - 订单对象
         */
        async addOrd(ord: Partial<Ord>) {
            const {data: {statusCode, code, list, message}} = await addOrd(ord);
            if (statusCode === 200) {
                ElMessage.success("添加成功");
                await this.fetchOrds();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 删除指定ID的订单
         * @param id - 订单ID
         */
        async deleteOrd(id: number) {
            const {data: {statusCode, code, list, message}} = await deleteOrd(id);
            if (statusCode === 200) {
                await this.fetchOrds();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 更新订单信息
         * @param ord - 订单对象
         */
        async updateOrd(ord: Partial<Ord>) {
            const {data: {statusCode, code, list, message}} = await updateOrd(ord);
            if (statusCode === 200) {
                await this.fetchOrds();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 设置当前订单为空
         */
        setCurrentOrdNull() {
            this.currentOrd = null;
        },
    }
});

// Cla Store
export const useClaStore = defineStore('cla', {
    state: () => ({
        classes: [] as Cla[] | null,
        currentClass: ref<Cla>({
            cla_id: null,
            cla_name: null,
            teach_id: null,
            cla_time: null,
        }),
    }),
    actions: {
        /**
         * 获取所有课程
         */
        async fetchClasses() {
            const {data: {statusCode, code, list, message}} = await getAllClas();
            if (statusCode === 200) {
                this.classes = list as Cla[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 添加新课程
         * @param cla - 课程对象
         */
        async addCla() {
            const {data: {statusCode, code, list, message}} = await addCla(this.currentClass);
            if (statusCode === 200) {
                this.setCurrentClassNull()
                await this.fetchClasses()
                ElMessage.success("添加成功");
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 根据教师ID获取课程
         * @param teachId - 教师ID
         */
        async getClasByTeacherId(teachId: number) {
            this.classes = null;
            const {data: {statusCode, code, list, message}} = await getClasByTeacherId(teachId);
            if (statusCode === 200) {
                this.classes = list as Cla[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 删除指定ID的课程
         * @param id - 课程ID
         */
        async deleteCla(id: number) {
            const {data: {statusCode, code, list, message}} = await deleteCla(id);
            if (statusCode === 200) {
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 更新课程信息
         * @param cla - 课程对象
         */
        async updateCla(cla: Partial<Cla>) {
            const {data: {statusCode, code, list, message}} = await updateCla(cla);
            if (statusCode === 200) {
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 设置当前课程为空
         */
        setCurrentClassNull() {
            this.currentClass = {
                cla_id: null,
                cla_name: null,
                teach_id: null,
                cla_time: null,
            };
        },
    }
});

// Sela Store
export const useSelaStore = defineStore('sela', {
    state: () => ({
        selas: [] as Sela[] | null,
        currentSela: null as any | null,
    }),
    actions: {
        /**
         * 获取所有选课记录
         */
        async fetchSelas() {
            const {data: {statusCode, code, list, message}} = await getAllSelas();
            if (statusCode === 200) {
                this.selas = list as Sela[];
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 添加新选课记录
         * @param sela - 选课对象
         */
        async addSela(sela: Partial<Sela>) {
            const {data: {statusCode, code, list, message}} = await addSela(sela);
            if (statusCode === 200) {
                ElMessage.success("添加成功");
                await this.fetchSelas();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 删除指定ID的选课记录
         * @param id - 选课ID
         */
        async deleteSela(id: number) {
            const {data: {statusCode, code, list, message}} = await deleteSela(id);
            if (statusCode === 200) {
                await this.fetchSelas();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 更新选课记录信息
         * @param sela - 选课对象
         */
        async updateSela(sela: Partial<Sela>) {
            const {data: {statusCode, code, list, message}} = await updateSela(sela);
            if (statusCode === 200) {
                await this.fetchSelas();
            } else {
                ElMessage.error(message);
            }
        },

        /**
         * 设置当前选课记录为空
         */
        setCurrentSelaNull() {
            this.currentSela = null;
        },
    }
});
