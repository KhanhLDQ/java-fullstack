import apiClient from "../api/apiClient";
import { redirect } from "react-router-dom";

export async function contactAction({ request, params }) {
  const data = await request.formData();

  const contactData = {
    name: data.get("name"), //get value from user input via name attribute
    email: data.get("email"),
    mobileNumber: data.get("mobileNumber"),
    message: data.get("message"),
  };

  try {
    await apiClient.post("/contacts", contactData);
    return { success: true };
    // return redirect("/home");
  } catch (error) {
    if (error.response?.status == 400) {
      return { success: false, errors: error.response?.data };
    }

    throw new Response(
      error.response?.data?.errorMessage ||
        error.message ||
        "Failed to submit your message. Please try again!!",
      { status: error.status || 500 }
    );
  }
}
