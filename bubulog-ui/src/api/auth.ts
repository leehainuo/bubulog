// 登录
export const login = async (
  username: string, 
  password: string
): Promise<Response> => {
  const res = await fetch("/api/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  });
  return res
};
