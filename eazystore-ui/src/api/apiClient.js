import axios from "axios";

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_EAZYSTORE_BE_BASE_URL,
  headers: {
    Accept: "application/json", //accept response in JSON format | not in HTML
  },
  timeout: 10000, //10 seconds
});

export default apiClient;
