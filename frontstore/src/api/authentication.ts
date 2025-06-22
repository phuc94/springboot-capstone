import axiosInstance from "@/vendor/axios";

export const register = async (user: any) => {
  const { data } = await axiosInstance.post('/auth/register', user);
  return data;
};

export const login = async (user: any) => {
  const { data } = await axiosInstance.post('/auth/login', user);
  return data;
};

export const getMe = async () => {
  const { data } = await axiosInstance.get('/auth/me');
  return data;
};

