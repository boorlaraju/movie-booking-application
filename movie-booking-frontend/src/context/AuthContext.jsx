
import  React,{ createContext, useContext, useMemo, useState } from 'react';
import api from '../api/client';

const AuthContext = createContext(null);
const readUser = () => {
  try { return JSON.parse(localStorage.getItem('cineverse_user') || 'null'); }
  catch { return null; }
};

export function AuthProvider({ children }) {
  const [user, setUser] = useState(readUser);
  const [token, setToken] = useState(() => localStorage.getItem('cineverse_token'));

  const login = async (credentials) => {
    const { data } = await api.post('/api/auth/login', credentials);
    if (!data?.jwtToken) throw new Error('Login response did not contain jwtToken. Check LoginResponseDTO.');
    const nextUser = { username: data.username, roles: data.role || [], id: null };
    localStorage.setItem('cineverse_token', data.jwtToken);
    localStorage.setItem('cineverse_user', JSON.stringify(nextUser));
    setToken(data.jwtToken); setUser(nextUser);
    return nextUser;
  };

  const register = async (details) => {
    const { data } = await api.post('/api/auth/registernormaluser', details);
    // This controller returns User, which includes the database ID.
    if (data?.id != null) {
      const nextUser = { username: data.username || details.username, id: data.id, roles: data.roles || ['ROLE_USER'] };
      localStorage.setItem('cineverse_pending_user', JSON.stringify(nextUser));
    }
    return data;
  };

  const logout = () => {
    localStorage.removeItem('cineverse_token'); localStorage.removeItem('cineverse_user');
    setToken(null); setUser(null);
  };

  const value = useMemo(() => ({ user, token, login, register, logout, isAdmin: Boolean(user?.roles?.includes('ROLE_ADMIN') || user?.roles?.includes('ADMIN')) }), [user, token]);
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
export const useAuth = () => useContext(AuthContext);
