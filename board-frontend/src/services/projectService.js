import api from '../api/api.js';

const STORAGE_KEYS = { PROJECTS: 'tb_projects' };
const storage = {
  load: (k, f) => JSON.parse(localStorage.getItem(k) || JSON.stringify(f)),
  save: (k, d) => localStorage.setItem(k, JSON.stringify(d)),
};

export const projectService = {
  async create(project) {
    try {
      await api.post('/api/project/create', project);
      return project;
    } catch {
      const projects = storage.load(STORAGE_KEYS.PROJECTS, []);
      projects.push(project);
      storage.save(STORAGE_KEYS.PROJECTS, projects);
      return project;
    }
  },

  async fetchAll() {
    try {
      const res = await api.get('/api/project/fetchAll');
      return Array.isArray(res) ? res : res?.data || [];
    } catch {
      return storage.load(STORAGE_KEYS.PROJECTS, []);
    }
  },
};