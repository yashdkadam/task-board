import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth.js';

export default function Login() {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState('');

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      await login(form);
      navigate('/board');
    } catch (err) {
      setError(err.message || 'Login failed');
    }
  }

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <div className="bg-white p-8 rounded shadow w-96">
        <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>
        {error && <p className="text-red-600 mb-2">{error}</p>}

        <form onSubmit={handleSubmit} className="flex flex-col gap-3">
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

          <button type="submit" className="bg-indigo-600 text-white p-2 rounded mt-2">
            Login
          </button>
        </form>

        <p className="text-center mt-4 text-sm">
          Don’t have an account?{' '}
          <span
            onClick={() => navigate('/register')}
            className="text-indigo-600 cursor-pointer"
          >
            Register
          </span>
        </p>
      </div>
    </div>
  );
}