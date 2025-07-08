import { AccessControlProvider } from "@refinedev/core";

export const accessControlProvider: AccessControlProvider = {
  // can: async ({ resource, action, params }) => {
  can: async (data) => {
    console.log(data); // products, orders, etc.

    if (true) {
      return { can: true };
    }

    return {
      can: false,
      reason: "Unauthorized",
    };
  },
};
