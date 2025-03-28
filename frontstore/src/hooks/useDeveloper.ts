import { fetchDevelopers } from "@/api/developerApi";
import { useQuery } from "@tanstack/react-query";

export const useDevelopers = () => {
  return useQuery({
    queryKey: ["developers"],
    queryFn: fetchDevelopers,
  });
};

