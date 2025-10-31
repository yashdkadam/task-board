import { useEffect, useState } from 'react';
import { userService } from '../services/userService.js';

const AUTH_KEY = 'tb_auth';

export function useAuth() {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem(AUTH_KEY)));

  useEffect(() => {
    const handler = () => setUser(JSON.parse(localStorage.getItem(AUTH_KEY)));
    window.addEventListener('storage', handler);
    return () => window.removeEventListener('storage', handler);
  }, []);

  return {
    user,
    async register(creds) {
      const userDto = {
        userId: Date.now(),
        username: creds.username,
        email: creds.email,
        passwordHash: creds.password,
        role: creds.role || 'User',
      };
      await userService.create(userDto);
      return userDto;
    },
    async login({ email, password }) {
      const u = await userService.fetchByEmail(email);
      if (!u) throw new Error('User not found');
      if ((u.passwordHash || u.password) !== password) throw new Error('Invalid credentials');
      localStorage.setItem(AUTH_KEY, JSON.stringify(u));
      setUser(u);
      return u;
    },
    logout() {
      localStorage.removeItem(AUTH_KEY);
      setUser(null);
    },
  };
}