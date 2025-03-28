// TODO Change this to use axios, synchronize with other API
export async function loginUser(email: string, password: string) {

  const res = await fetch("https://api.example.com/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password }),
  });

  if (!res.ok) throw new Error("Invalid credentials");

  const data = await res.json();
  return { user: data.user, token: data.token };
}

