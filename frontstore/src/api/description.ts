import axios from "axios";

const API_URL = "http://phucserver:8080/api/game_description";

export const fetchDescriptionById = async (id: number) => {
  const { data } = await axios.get(`${API_URL}/${id}`);
  return data;
};

