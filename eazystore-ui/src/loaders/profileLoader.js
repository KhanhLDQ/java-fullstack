import apiClient from "../api/apiClient";

//loaders -> GET only [data fetching]
export async function profileLoader() {
  try {
    const response = await apiClient.get("/profile");
    return response.data;
  } catch (error) {
    throw new Response(
      error.response?.data?.errorMessage ||
        error.message ||
        "Failed to fetch profile. Please try again!!",
      { status: error.status || 500 },
    );
  }
}
