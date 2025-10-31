const API_BASE = 'http://localhost:8080';

async function parseApiResponse(resp) {
  const text = await resp.text();
  try {
    const json = JSON.parse(text || '{}');
    if (json && typeof json === 'object') {
      if ('data' in json) return json.data;
      return json;
    }
  } catch (e) {}
  return text;
}

const api = {
  async post(path, body) {
    const resp = await fetch(API_BASE + path, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body),
    });
    if (!resp.ok) throw new Error('API error ' + resp.status);
    return await parseApiResponse(resp);
  },

  async patch(path, body) {
    const resp = await fetch(API_BASE + path, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body),
    });
    if (!resp.ok) throw new Error('API error ' + resp.status);
    return await parseApiResponse(resp);
  },

  async get(path) {
    const resp = await fetch(API_BASE + path);
    if (!resp.ok) throw new Error('API error ' + resp.status);
    return await parseApiResponse(resp);
  },

  async del(path) {
    const resp = await fetch(API_BASE + path, { method: 'DELETE' });
    if (!resp.ok) throw new Error('API error ' + resp.status);
    return await parseApiResponse(resp);
  },
};

export default api;