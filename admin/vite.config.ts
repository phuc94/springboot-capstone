import react from "@vitejs/plugin-react";
import { defineConfig } from "vite";

export default defineConfig({
  plugins: [react()],
  server: {
    allowedHosts: ['phucserver']
  },
  define: {
    'process.env': {}
  }
});
