import api from '../api/api.js';

const STORAGE_KEYS = { USERS: 'tb_users' };

const storage = {
  load(key, fallback) {
    try {
      const raw = localStorage.getItem(key);
      return raw ? JSON.parse(raw) : fallback;
    } catch {
      return fallback;
    }
  },
  save(key, data) {
    localStorage.setItem(key, JSON.stringify(data));
  },
};

export const userService = {
  async create(user) {
    try {
      await api.post('/api/user/create', user);
      return user;
    } catch {
      const users = storage.load(STORAGE_KEYS.USERS, []);
      users.push(user);
      storage.save(STORAGE_KEYS.USERS, users);
      return user;
    }
  },

  async fetchByEmail(email) {
    try {
      const res = await api.get(`/api/user/fetch?email=${encodeURIComponent(email)}`);
      if (Array.isArray(res) && res.length > 0) return res[0];
      if (res.userId || res.username) return res;
      if (res.data) return res.data;
      return null;
    } catch {
      const users = storage.load(STORAGE_KEYS.USERS, []);
      return users.find((u) => u.email === email) || null;
    }
  },

  async fetchAll() {
    try {
      const res = await api.get('/api/user/fetchAll');
      return Array.isArray(res) ? res : res?.data || [];
    } catch {
      return storage.load(STORAGE_KEYS.USERS, []);
    }
  },
};