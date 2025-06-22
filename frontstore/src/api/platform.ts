import axiosInstance from "@/vendor/axios";

export const getPlatforms = async () => {
  const { data } = await axiosInstance.get(`/platform`);
  return data;
};

