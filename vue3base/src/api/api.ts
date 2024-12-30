import axios from 'axios';
import type { User, Comment, MType, Stow, Message, Mode, UAdmin } from '@/types/type';

const API_URL = 'http://localhost:3000';
// const API_URL = 'https://cn-bj-plc-2.ofalias.net:54284';

// User API
export const fetchUsers = () => axios.get(`${API_URL}/user/getAllUsers`);
export const addUser = (user: User) => axios.post(`${API_URL}/user/addUser`, user);
export const deleteUser = (id: number) => axios.post(`${API_URL}/user/delete`, { id });
export const loginUser = (userForm: User) => axios.post(`${API_URL}/user/login`, userForm);
export const updateUser = (user: User) => axios.post(`${API_URL}/user/update`, user);
export const fetchUserById = (id: number) => axios.get(`${API_URL}/user/${id}`);

// Comment API
export const getCommentsByMid = (mid: number) => axios.get(`${API_URL}/comment/getCommentsByMid/${ mid }`,);
export const addComment = (comment: Comment) => axios.post(`${API_URL}/comment/addComment`, comment);
export const deleteComment = (id: number) => axios.post(`${API_URL}/comment/deleteComment`, { id });
export const getAllComments = () => axios.post(`${API_URL}/comment/getAllComments`);
export const updateComment = (comment: Comment) => axios.post(`${API_URL}/comment/updateComment`, comment);

// MType API
export const getAllMTypes = () => axios.get(`${API_URL}/mtype/getAllMTypes`);
export const getMTypeById = (id: number) => axios.get(`${API_URL}/mtype/getMTypeById/${id}`);

// Stow API
export const addStow = (stow: Stow) => axios.post(`${API_URL}/stow/addStow`, stow);
export const deleteStow = (id: number) => axios.delete(`${API_URL}/stow/deleteStow/${id}`);
export const getAllStows = () => axios.get(`${API_URL}/stow/getAllStows`);
export const getStowById = (id: number) => axios.get(`${API_URL}/stow/getStowById/${id}`);
export const getStowsByUid = (uid: number) => axios.get(`${API_URL}/stow/getStowsByUid/${uid}`);
export const updateStow = (stow: Stow) => axios.put(`${API_URL}/stow/updateStow`, stow);

// Message API
export const addMessage = (message: Message) => axios.post(`${API_URL}/message/add`, message);
export const getAllMessages = () => axios.get(`${API_URL}/message/all`);
export const getMessagesByToUid = (utoid: number) => axios.get(`${API_URL}/message/byToUid/${utoid}`);
export const getMessagesByUid = (uid: number) => axios.get(`${API_URL}/message/byUid/${uid}`);
export const deleteMessage = (id: number) => axios.post(`${API_URL}/message/delete`, { MEid: id });
export const updateMessage = (message: Message) => axios.post(`${API_URL}/message/update`, message);

// Mode API
export const addMode = (mode: Mode) => axios.post(`${API_URL}/mode/addMode`, mode);
export const deleteMode = (id: number) => axios.delete(`${API_URL}/mode/deleteMode/${id}`);
export const getAllModes = () => axios.get(`${API_URL}/mode/getAllModes`);
export const getModeByIdAndUpdateSum = (id: number) => axios.get(`${API_URL}/mode/getModeByIdAndUpdateSum/${id}`);
export const getModesByTid = (tid: number) => axios.get(`${API_URL}/mode/getModesByTid/${tid}`);
export const getModesByUid = (uid: number) => axios.get(`${API_URL}/mode/getModesByUid/${uid}`);
export const updateMode = (mode: Mode) => axios.put(`${API_URL}/mode/updateMode`, mode);

// UAdmin API
export const uAdminLogin = (UAdmForm: UAdmin) => axios.post(`${API_URL}/uadmin/login`, UAdmForm);