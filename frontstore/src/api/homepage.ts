import axios from "axios";
import { API_URL } from "./constant";

export const fetchPlatformGames = async () => {
  const { data } = await axios.get(`${API_URL}/home`);
  return data;
};

