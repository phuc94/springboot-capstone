import axios from "axios";

const API_URL = "http://phucserver:8080/api/home";

export const fetchPlatformGames = async () => {
  const { data } = await axios.get(API_URL);
  return data;
};

