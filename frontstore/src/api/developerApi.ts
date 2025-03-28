import axios from "axios";

const API_URL = "http://localhost:8080/api/developer";

export const fetchDevelopers = async () => {
  console.log('xxxxxxxxxxxxxx')
  const { data } = await axios.get(API_URL);
  return data;
};

export const fetchDeveloperById = async (id: number) => {
  const { data } = await axios.get(`${API_URL}/${id}`);
  return data;
};

export const createDeveloper = async (developer: any) => {
  const { data } = await axios.post(API_URL, developer);
  return data;
};

