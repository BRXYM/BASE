import { defineStore } from 'pinia';
import { fetchUsers, addUser, deleteUser, loginUser, updateUser, fetchUserById } from '@/api/index';
import type { User, Response } from '@/types';
import { ElMessage } from 'element-plus'

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
      this.users = await fetchUsers() as unknown as User[];
    },
    /**
     * 添加新用户
     * @param user - 用户对象
     */
    async regist(user: User) {
      const {data:{statusCode,code,list,message}} = await addUser(user);
      if(statusCode === 200){
        ElMessage.success("注册成功");
        this.islogin = 2;
      }else{
        ElMessage.error(message);
      }
      // this.fetchUsers();
    },
    /**
     * 删除指定ID的用户
     * @param id - 用户ID
     */
    async deleteUser(id: number) {
      await deleteUser(id);
      this.fetchUsers();
    },
    /**
     * 用户登录
     * @param userForm - 用户登录表单
     */
    async login(userForm: User) {
      const {data:{statusCode,code,list,message}} = (await loginUser(userForm));
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
      await updateUser(user);
      this.fetchUsers();
    },
    /**
     * 根据ID获取用户
     * @param id - 用户ID
     */
    async fetchUserById(id: number) {
      this.currentUser = await fetchUserById(id) as unknown as User;
    },
    /**
     * 设置用户为空
     */
    setUserNull() {
      this.currentUser = null;
      this.islogin = 0;
    },
  },
});


import { getCommentsByMid, addComment, deleteComment, getAllComments, updateComment } from '@/api/index';
import type { Comment } from '@/types';


export const useCommentStore = defineStore('comment', {
  state: () => ({
    comments: [] as Comment[] | null,
  }),
  actions: {
    /**
     * 根据MID获取评论
     * @param mid - MID
     */
    async getCommentsByMid(mid: number) {
      this.comments = await getCommentsByMid(mid) as unknown as Comment[];
    },
    /**
     * 添加评论
     * @param comment - 评论对象
     */
    async addComment(comment: Comment) {
      await addComment(comment);
      this.getCommentsByMid(comment.MOid as number);
    },
    /**
     * 删除指定ID的评论
     * @param id - 评论ID
     */
    async deleteComment(id: number) {
      await deleteComment(id);
      this.getAllComments();
    },
    /**
     * 获取所有评论
     */
    async getAllComments() {
      this.comments = await getAllComments() as unknown as Comment[];
    },
    /**
     * 更新评论
     * @param comment - 评论对象
     */
    async updateComment(comment: Comment) {
      await updateComment(comment);
      this.getCommentsByMid(comment.MOid as number);
    },
  },
});


import { getAllMTypes, getMTypeById } from '@/api/index';
import type { MType } from '@/types';


export const useMTypeStore = defineStore('mtype', {
  state: () => ({
    mtypes: [] as MType[] | null,
    currentMType: null as MType | null ,
  }),
  actions: {
    /**
     * 获取所有MType
     */
    async getAllMTypes() {
      this.mtypes = await getAllMTypes() as unknown as MType[];
    },
    /**
     * 根据ID获取MType
     * @param id - MType ID
     */
    async getMTypeById(id: number) {
      this.currentMType = await getMTypeById(id) as unknown as MType;
    },
  },
});


import { addStow, deleteStow, getAllStows, getStowById, getStowsByUid, updateStow } from '@/api/index';
import type { Stow } from '@/types';


export const useStowStore = defineStore('stow', {
  state: () => ({
    stows: [] as Stow[] | null,
    currentStow: null as Stow | null ,
  }),
  actions: {
    /**
     * 添加收藏
     * @param stow - 收藏对象
     */
    async addStow(stow: Stow) {
      await addStow(stow);
      this.getAllStows();
    },
    /**
     * 删除指定ID的收藏
     * @param id - 收藏ID
     */
    async deleteStow(id: number) {
      await deleteStow(id);
      this.getAllStows();
    },
    /**
     * 获取所有收藏
     */
    async getAllStows() {
      this.stows = await getAllStows() as unknown as Stow[];
    },
    /**
     * 根据ID获取收藏
     * @param id - 收藏ID
     */
    async getStowById(id: number) {
      this.currentStow = await getStowById(id) as unknown as Stow;
    },
    /**
     * 根据UID获取收藏
     * @param uid - 用户ID
     */
    async getStowsByUid(uid: number) {
      this.stows = await getStowsByUid(uid) as unknown as Stow[];
    },
    /**
     * 更新收藏
     * @param stow - 收藏对象
     */
    async updateStow(stow: Stow) {
      await updateStow(stow);
      this.getAllStows();
    },
  },
});


import { addMessage, getAllMessages, getMessagesByToUid, getMessagesByUid, deleteMessage, updateMessage } from '@/api/index';
import type { Message } from '@/types';


export const useMessageStore = defineStore('message', {
  state: () => ({
    messages: [] as Message[] | null,
    currentMessage: null as Message | null ,
  }),
  actions: {
    /**
     * 添加消息
     * @param message - 消息对象
     */
    async addMessage(message: Message) {
      await addMessage(message);
      this.getAllMessages();
    },
    /**
     * 获取所有消息
     */
    async getAllMessages() {
      this.messages = await getAllMessages() as unknown as Message[];
    },
    /**
     * 根据接收者UID获取消息
     * @param uid - 接收者UID
     */
    async getMessagesByToUid(uid: number) {
      this.messages = await getMessagesByToUid(uid) as unknown as Message[];
    },
    /**
     * 根据发送者UID获取消息
     * @param uid - 发送者UID
     */
    async getMessagesByUid(uid: number) {
      this.messages = await getMessagesByUid(uid) as unknown as Message[];
    },
    /**
     * 删除指定ID的消息
     * @param id - 消息ID
     */
    async deleteMessage(id: number) {
      await deleteMessage(id);
      this.getAllMessages();
    },
    /**
     * 更新消息
     * @param message - 消息对象
     */
    async updateMessage(message: Message) {
      await updateMessage(message);
      this.getAllMessages();
    },
  },
});


import { addMode, deleteMode, getAllModes, getModeByIdAndUpdateSum, getModesByTid, getModesByUid, updateMode } from '@/api/index';
import type { Mode } from '@/types';


export const useModeStore = defineStore('mode', {
  state: () => ({
    modes: [] as Mode[] | null,
    currentMode: null as Mode | null ,
  }),
  actions: {
    /**
     * 添加模式
     * @param mode - 模式对象
     */
    async addMode(mode: Mode) {
      await addMode(mode);
      this.getAllModes();
    },
    /**
     * 删除指定ID的模式
     * @param id - 模式ID
     */
    async deleteMode(id: number) {
      await deleteMode(id);
      this.getAllModes();
    },
    /**
     * 获取所有模式
     */
    async getAllModes() {
      this.modes = await getAllModes() as unknown as Mode[];
    },
    /**
     * 根据ID获取模式并更新统计信息
     * @param id - 模式ID
     */
    async getModeByIdAndUpdateSum(id: number) {
      this.currentMode = await getModeByIdAndUpdateSum(id) as unknown as Mode;
    },
    /**
     * 根据TID获取模式
     * @param tid - TID
     */
    async getModesByTid(tid: number) {
      this.modes = await getModesByTid(tid) as unknown as Mode[];
    },
    /**
     * 根据UID获取模式
     * @param uid - 用户ID
     */
    async getModesByUid(uid: number) {
      this.modes = await getModesByUid(uid) as unknown as Mode[];
    },
    /**
     * 更新模式
     * @param mode - 模式对象
     */
    async updateMode(mode: Mode) {
      await updateMode(mode);
      this.getAllModes();
    },
  },
});


import { uAdminLogin } from '@/api/index';
import type { UAdmin } from '@/types';


export const useUAdminStore = defineStore('uadmin', {
  state: () => ({
    currentUAdmin: null as UAdmin | null ,
  }),
  actions: {
    /**
     * 管理员登录
     * @param UAdmForm - 管理员登录表单
     */
    async uAdminLogin(UAdmForm: UAdmin) {
      this.currentUAdmin = await uAdminLogin(UAdmForm) as unknown as UAdmin;
    },
  },
});