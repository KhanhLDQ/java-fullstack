import apiClient from "../api/apiClient";

export async function profileAction({ request }) {
  const data = await request.formData();

  const profileData = {
    name: data.get("name"),
    email: data.get("email"),
    mobileNumber: data.get("mobileNumber"),
    street: data.get("street"),
    city: data.get("city"),
    state: data.get("state"),
    postalCode: data.get("postalCode"),
    country: data.get("country"),
  };

  try {
    const response = await apiClient.put("/profile", profileData);
    return { success: true, profileData: response.data };
  } catch (error) {
    if (error.response?.status === 400) {
      return { success: false, errors: error.response?.data };
    }

    throw new Response(
      error.response?.data?.errorMessage ||
        error.message ||
        "Failed to update profile. Please try again!!",
      { status: error.response?.status || 500 },
    );
  }
}
