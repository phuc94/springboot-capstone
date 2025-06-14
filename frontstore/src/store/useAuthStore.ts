import { create } from "zustand";
import { persist } from 'zustand/middleware'

interface AuthState {
  user: User | null;
  token: string | null;
  isAuthenticated: boolean;
  login: (user: User, token: string) => void;
  logout: () => void;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({ user: null,
      token: null,
      isAuthenticated: false,
      login: (user: User, token) => set({ user, token, isAuthenticated: true }),
      logout: () => set({ user: null, token: null, isAuthenticated: false }),
    }),
    {
      name: "user_info",
    }
  )
);

interface User {
  id: number,
  name: string,
  email: string
}

