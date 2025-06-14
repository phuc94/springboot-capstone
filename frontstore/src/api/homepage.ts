import axiosInstance from "@/vendor/axios";

export const fetchPlatformGames = async () => {
  const { data } = await axiosInstance.get('/home');
  return data;
};

