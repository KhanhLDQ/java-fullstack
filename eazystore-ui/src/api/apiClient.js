import axios from "axios";

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_EAZYSTORE_BE_BASE_URL,
  headers: {
    Accept: "application/json", //accept response in JSON format | not in HTML
    "Content-Type": "application/json",
  },
  timeout: 10000, //10 seconds
});

apiClient.interceptors.request.use(
  (config) => {
    const jwt = localStorage.getItem("jwt");
    if (jwt) {
      config.headers.Authorization = `Bearer ${jwt}`;
    }

    return config;
  },
  (error) => Promise.reject(error),
);

export default apiClient;
