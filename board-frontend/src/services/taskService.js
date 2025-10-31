import api from '../api/api.js';

const STORAGE_KEYS = { TASKS: 'tb_tasks' };
const storage = {
  load: (k, f) => JSON.parse(localStorage.getItem(k) || JSON.stringify(f)),
  save: (k, d) => localStorage.setItem(k, JSON.stringify(d)),
};

export const taskService = {
  async create(task) {
    try {
      await api.post('/api/task/create', task);
      return task;
    } catch {
      const tasks = storage.load(STORAGE_KEYS.TASKS, []);
      tasks.push(task);
      storage.save(STORAGE_KEYS.TASKS, tasks);
      return task;
    }
  },

  async fetchAll() {
    try {
      const res = await api.get('/api/task/fetchAll');
      return Array.isArray(res) ? res : res?.data || [];
    } catch {
      return storage.load(STORAGE_KEYS.TASKS, []);
    }
  },
};