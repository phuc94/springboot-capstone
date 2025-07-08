import { AuthProvider } from "@refinedev/core";

const DOMAIN = "http://phucserver:8080"

export const authProvider: AuthProvider = {
  register: async ({ email, password }) =>  {
    const response = await fetch(
      `${DOMAIN}/admin/register`,
      {
        method: "POST",
        body: JSON.stringify({ email, password }),
        headers: {
          "Content-Type": "application/json",
        },
      },
    );

    const data = await response.json();
    if (data.code === 200) {
      return { success: true, redirectTo: "/login" };
    }

    return { success: false };
  },
  login: async ({ email, password }) => {
    const response = await fetch(
      `${DOMAIN}/admin/login`,
      {
        method: "POST",
        body: JSON.stringify({ email, password }),
        headers: {
          "Content-Type": "application/json",
        },
      },
    );

    const data = await response.json();
    if (data.data.token) {
      localStorage.setItem("my_access_token", data.data.token);
      return { success: true, redirectTo: "/" };
    }

    return { success: false };
  },
  onError: async (error) => {
    if (error?.status === 401) {
      return {
        logout: true,
        error: error
      };
    }

    return {error};
  },
  check: async () => {
    const token = localStorage.getItem("my_access_token"); // TODO: call backend to check token

    return { authenticated: Boolean(token) };
  },
   // login method receives an object with all the values you've provided to the useLogin hook.
  logout: async () => {
    localStorage.removeItem("my_access_token");
    // We're returning success: true to indicate that the logout operation was successful.
    return {
      success: true,
      redirectTo: "/"
    };
  },
  getIdentity: async () => {
    const response = await fetch("https://api.fake-rest.refine.dev/auth/me", {
      headers: {
        Authorization: localStorage.getItem("my_access_token") || "",
      },
    });

    if (response.status < 200 || response.status > 299) {
      return null;
    }

    const data = await response.json();

    return data;
  },
};
