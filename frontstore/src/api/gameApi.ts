import axios from "axios";

const API_URL = "http://phucserver:8080/api/game";

export const fetchGames = async () => {
  const { data } = await axios.get(API_URL);
  return data;
};

export const fetchGameDetail = async (id: number) => {
  const { data } = await axios.get(`${API_URL}/${id}`);
  return data;
};

export const createGame = async (product: any) => {
  const { data } = await axios.post(API_URL, product);
  return data;
};

