import axios from "axios";
import { API_URL } from "./constant";

export const register = async (user: any) => {
  const { data } = await axios.post(`${API_URL}/auth/register`, user);
  return data;
};

export const login = async (user: any) => {
  const { data } = await axios.post(`${API_URL}/auth/login`, user);
  return data;
};

