import apiClient from "../api/apiClient";

//loaders -> GET only [data fetching]
export async function productsLoader() {
  try {
    const response = await apiClient.get("/products");
    return response.data;
  } catch (error) {
    throw new Response(
      error.message || "Failed to fetch products. Please try again!!",
      { status: error.status || 500 }
    );
  }
}
