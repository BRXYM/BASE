import axios from 'axios';

const API_URL = 'http://localhost:3000';

export const getTableInforApi = () =>axios.get(`${API_URL}/admController/getTableInfo`);