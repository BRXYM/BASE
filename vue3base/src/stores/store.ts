import { defineStore } from 'pinia';
import { fetchUsers, addUser, deleteUser, loginUser, updateUser, fetchUserById } from '@/api/api';
import type { User, Response } from '@/types/type';
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
      const { data: { statusCode, code, list, message } } = await fetchUsers();
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
      const { data: { statusCode, code, list, message } } = await addUser(user);
      if (statusCode === 200) {
        ElMessage.success("注册成功");
        this.islogin = 2;
      } else {
        ElMessage.error(message);
      }
      // this.fetchUsers();
    },
    /**
     * 删除指定ID的用户
     * @param id - 用户ID
     */
    async deleteUser(id: number) {
      const { data: { statusCode, code, list, message } } = await deleteUser(id);
      if (statusCode === 200) {
        this.fetchUsers();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 用户登录
     * @param userForm - 用户登录表单
     */
    async login(userForm: User) {
      const { data: { statusCode, code, list, message } } = await loginUser(userForm);
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
      const { data: { statusCode, code, list, message } } = await updateUser(user);
      if (statusCode === 200) {
        this.fetchUsers();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据ID获取用户
     * @param id - 用户ID
     */
    async fetchUserById(id: number) {
      const { data: { statusCode, code, list, message } } = await fetchUserById(id);
      if (statusCode === 200) {
        return list;
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
  },
});


import { getCommentsByMid, addComment, deleteComment, getAllComments, updateComment } from '@/api/api';
import type { Comment } from '@/types/type';


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
      const { data: { statusCode, code, list, message } } = await getCommentsByMid(mid);
      if (statusCode === 200) {
        this.comments = list as Comment[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 添加评论
     * @param comment - 评论对象
     */
    async addComment(comment: Comment) {
      const { data: { statusCode, code, list, message } } = await addComment(comment);
      if (statusCode === 200) {
        this.getCommentsByMid(comment.MOid as number);
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 删除指定ID的评论
     * @param id - 评论ID
     */
    async deleteComment(id: number) {
      const { data: { statusCode, code, list, message } } = await deleteComment(id);
      if (statusCode === 200) {
        // this.getAllComments();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 获取所有评论
     */
    async getAllComments() {
      const { data: { statusCode, code, list, message } } = await getAllComments();
      if (statusCode === 200) {
        this.comments = list as Comment[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 更新评论
     * @param comment - 评论对象
     */
    async updateComment(comment: Comment) {
      const { data: { statusCode, code, list, message } } = await updateComment(comment);
      if (statusCode === 200) {
        // this.getCommentsByMid(comment.MOid as number);
      } else {
        ElMessage.error(message);
      }
    },
  },
});


import { getAllMTypes, getMTypeById } from '@/api/api';
import type { MType } from '@/types/type';


export const useMTypeStore = defineStore('mtype', {
  state: () => ({
    mtypes: [] as MType[] | null,
    currentMType: null as MType | null,
  }),
  actions: {
    /**
     * 获取所有MType
     */
    async getAllMTypes() {
      const { data: { statusCode, code, list, message } } = await getAllMTypes();
      if (statusCode === 200) {
        this.mtypes = list as MType[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据ID获取MType
     * @param id - MType ID
     */
    async getMTypeById(id: number) {
      const { data: { statusCode, code, list, message } } = await getMTypeById(id);
      if (statusCode === 200) {
        this.currentMType = list;
      } else {
        ElMessage.error(message);
      }
    },
  },
});


import { addStow, deleteStow, getAllStows, getStowById, getStowsByUid, updateStow } from '@/api/api';
import type { Stow } from '@/types/type';


export const useStowStore = defineStore('stow', {
  state: () => ({
    stows: [] as Stow[] | null,
    currentStow: null as Stow | null,
  }),
  actions: {
    /**
     * 添加收藏
     * @param stow - 收藏对象
     */
    async addStow(stow: Stow) {
      const { data: { statusCode, code, list, message } } = await addStow(stow);
      if (statusCode === 200) {
        this.getAllStows();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 删除指定ID的收藏
     * @param id - 收藏ID
     */
    async deleteStow(id: number) {
      const { data: { statusCode, code, list, message } } = await deleteStow(id);
      if (statusCode === 200) {
        await this.getAllStows();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 获取所有收藏
     */
    async getAllStows() {
      const { data: { statusCode, code, list, message } } = await getAllStows();
      if (statusCode === 200) {
        this.stows = list as Stow[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据ID获取收藏
     * @param id - 收藏ID
     */
    async getStowById(id: number) {
      const { data: { statusCode, code, list, message } } = await getStowById(id);
      if (statusCode === 200) {
        this.currentStow = list;
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据UID获取收藏
     * @param uid - 用户ID
     */
    async getStowsByUid(uid: number) {
      const { data: { statusCode, code, list, message } } = await getStowsByUid(uid);
      if (statusCode === 200) {
        this.stows = list as Stow[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 更新收藏
     * @param stow - 收藏对象
     */
    async updateStow(stow: Stow) {
      const { data: { statusCode, code, list, message } } = await updateStow(stow);
      if (statusCode === 200) {
        this.getAllStows();
      } else {
        ElMessage.error(message);
      }
    },
  },
});


import { addMessage, getAllMessages, getMessagesByToUid, getMessagesByUid, deleteMessage, updateMessage } from '@/api/api';
import type { Message } from '@/types/type';


export const useMessageStore = defineStore('message', {
  state: () => ({
    messages: [] as Message[] | null,
    currentMessage: null as Message | null,
  }),
  actions: {
    /**
     * 添加消息
     * @param message - 消息对象
     */
    async addMessage(message: Message) {
      const { data: { statusCode, code, list, message: msg } } = await addMessage(message);
      if (statusCode === 200) {
        // this.getAllMessages();
      } else {
        ElMessage.error(msg);
      }
    },
    /**
     * 获取所有消息
     */
    async getAllMessages() {
      const { data: { statusCode, code, list, message } } = await getAllMessages();
      if (statusCode === 200) {
        this.messages = list as Message[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据接收者UID获取消息
     * @param uid - 接收者UID
     */
    async getMessagesByToUid(uid: number) {
      const { data: { statusCode, code, list, message } } = await getMessagesByToUid(uid);
      if (statusCode === 200) {
        this.messages = list as Message[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据发送者UID获取消息
     * @param uid - 发送者UID
     */
    async getMessagesByUid(uid: number) {
      const { data: { statusCode, code, list, message } } = await getMessagesByUid(uid);
      if (statusCode === 200) {
        this.messages = list as Message[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 删除指定ID的消息
     * @param id - 消息ID
     */
    async deleteMessage(id: number) {
      const { data: { statusCode, code, list, message } } = await deleteMessage(id);
      if (statusCode === 200) {
        // this.getAllMessages();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 更新消息
     * @param message - 消息对象
     */
    async updateMessage(message: Message) {
      const { data: { statusCode, code, list, message: msg } } = await updateMessage(message);
      if (statusCode === 200) {
        // this.getAllMessages();
      } else {
        ElMessage.error(msg);
      }
    },
  },
});


import { addMode, deleteMode, getAllModes, getModeByIdAndUpdateSum, getModesByTid, getModesByUid, updateMode } from '@/api/api';
import type { Mode } from '@/types/type';


export const useModeStore = defineStore('mode', {
  state: () => ({
    modes: [] as Mode[] | null,
    currentMode: null as Mode | null,
  }),
  actions: {
    /**
     * 添加模式
     * @param mode - 模式对象
     */
    async addMode(mode: Mode) {
      const { data: { statusCode, code, list, message } } = await addMode(mode);
      if (statusCode === 200) {
        // this.getAllModes();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 删除指定ID的模式
     * @param id - 模式ID
     */
    async deleteMode(id: number) {
      const { data: { statusCode, code, list, message } } = await deleteMode(id);
      if (statusCode === 200) {
        // this.getAllModes();
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 获取所有模式
     */
    async getAllModes() {
      const { data: { statusCode, code, list, message } } = await getAllModes();
      if (statusCode === 200) {
        this.modes = list as Mode[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据ID获取模式并更新统计信息
     * @param id - 模式ID
     */
    async getModeByIdAndUpdateSum(id: number) {
      const { data: { statusCode, code, list, message }} = await getModeByIdAndUpdateSum(id);
      if (statusCode === 200) {
        this.currentMode = list;
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据TID获取模式
     * @param tid - TID
     */
    async getModesByTid(tid: number) {
      const { data: { statusCode, code, list, message } } = await getModesByTid(tid);
      if (statusCode === 200) {
        this.modes = list as Mode[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 根据UID获取模式
     * @param uid - 用户ID
     */
    async getModesByUid(uid: number) {
      const { data: { statusCode, code, list, message } } = await getModesByUid(uid);
      if (statusCode === 200) {
        this.modes = list as Mode[];
      } else {
        ElMessage.error(message);
      }
    },
    /**
     * 更新模式
     * @param mode - 模式对象
     */
    async updateMode(mode: Mode) {
      const { data: { statusCode, code, list, message } } = await updateMode(mode);
      if (statusCode === 200) {
        this.getAllModes();
      } else {
        ElMessage.error(message);
      }
    },
  },
});


import { uAdminLogin } from '@/api/api';
import type { UAdmin } from '@/types/type';


export const useUAdminStore = defineStore('uadmin', {
  state: () => ({
    currentUAdmin: null as UAdmin | null,
  }),
  actions: {
    /**
     * 管理员登录
     * @param UAdmForm - 管理员登录表单
     */
    async uAdminLogin(UAdmForm: UAdmin) {
      const { data: { statusCode, code, list, message } } = await uAdminLogin(UAdmForm);
      if (statusCode === 200) {
        this.currentUAdmin = list;
      } else {
        ElMessage.error(message);
      }
    },
  },
});