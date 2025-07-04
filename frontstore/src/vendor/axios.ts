import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://phucserver:8080/api',
  timeout: 3000,
  headers: {
    'Content-Type': 'application/json',
  },
});

axiosInstance.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem("token");
      if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
      }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

 export default axiosInstance;
