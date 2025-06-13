import axios from "axios";
import { API_URL } from "./constant";

export const fetchGameDetail = async (id: number) => {
  const { data } = await axios.get(`${API_URL}/game/${id}`);
  return data;
};

