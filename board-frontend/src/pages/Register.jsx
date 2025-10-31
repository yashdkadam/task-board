import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth.js';

export default function Register() {
  const navigate = useNavigate();
  const { register } = useAuth();
  const [form, setForm] = useState({
    username: '',
    email: '',
    password: '',
    role: 'User',
  });
  const [error, setError] = useState('');

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      await register(form);
      navigate('/login');
    } catch (err) {
      setError(err.message || 'Registration failed');
    }
  }

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <div className="bg-white p-8 rounded shadow w-96">
        <h2 className="text-2xl font-bold mb-4 text-center">Register</h2>
        {error && <p className="text-red-600 mb-2">{error}</p>}

        <form onSubmit={handleSubmit} className="flex flex-col gap-3">
          <input
            type="text"
            placeholder="Username"
            value={form.username}
            onChange={(e) => setForm({ ...form, username: e.target.value })}
            className="p-2 border rounded"
            required
          />

          <input
            type="email"
            placeholder="Email"
            value={form.email}
            onChange={(e) => setForm({ ...form, email: e.target.value })}
            className="p-2 border rounded"
            required
          />

          <input
            type="password"
            placeholder="Password"
            value={form.password}
            onChange={(e) => setForm({ ...form, password: e.target.value })}
            className="p-2 border rounded"
            required
          />

          <select
            value={form.role}
            onChange={(e) => setForm({ ...form, role: e.target.value })}
            className="p-2 border rounded"
          >
            <option value="User">User</option>
            <option value="Manager">Manager</option>
          </select>

          <button type="submit" className="bg-indigo-600 text-white p-2 rounded mt-2">
            Register
          </button>
        </form>

        <p className="text-center mt-4 text-sm">
          Already have an account?{' '}
          <span
            onClick={() => navigate('/login')}
            className="text-indigo-600 cursor-pointer"
          >
            Login
          </span>
        </p>
      </div>
    </div>
  );
}