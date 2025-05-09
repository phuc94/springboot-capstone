import axios from "axios";

const API_URL = "http://localhost:8080/api/game_description";

export const fetchDescriptions = async () => {
  const { data } = await axios.get(API_URL);
  return data;
};

export const fetchDescriptionById = async (id: number) => {
  const { data } = await axios.get(`${API_URL}/${id}`);
  return data;
};

export const createDescription = async (developer: any) => {
  const { data } = await axios.post(API_URL, developer);
  return data;
};

