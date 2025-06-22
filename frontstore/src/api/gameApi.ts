import axiosInstance from "@/vendor/axios";

export const fetchGameDetail = async (id: number) => {
  const { data } = await axiosInstance.get(`/game/${id}`);
  return data;
};

export const fetchPlatformGameList = async (platformName: string) => {
  const { data } = await axiosInstance.get(`/platform/${platformName}`);
  return data;
};
