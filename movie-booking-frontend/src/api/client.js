import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8081',
  headers: { 'Content-Type': 'application/json' },
  timeout: 15000,
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('cineverse_token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.code === 'ECONNABORTED') error.userMessage = 'The server took too long to respond.';
    else if (!error.response) error.userMessage = 'Cannot connect to the backend. Make sure Spring Boot is running on port 8080.';
    else if (error.response.status === 401) error.userMessage = 'Your session may have expired. Please log in again.';
    else if (error.response.status === 403) error.userMessage = 'You do not have permission to perform this action.';
    else error.userMessage = error.response.data?.message || error.response.data?.error || `Request failed (${error.response.status}).`;
    return Promise.reject(error);
  }
);

export const getErrorMessage = (error) => error.userMessage || error.response?.data?.message || error.message || 'Something went wrong.';
export default api;
