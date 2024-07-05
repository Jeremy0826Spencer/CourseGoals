import axios, { isAxiosError } from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./LoginComponent.css";

type Login = { usernameOrEmail?: string; password?: string };

export const LoginComponent: React.FC = () => {
  const navigate = useNavigate();
  const [loginCredentials, setLoginCredentials] = useState<Login>({});

  const login = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/API/V1/auth/login",
        loginCredentials
      );
      const token = response.data.accessToken;
      localStorage.setItem("auth", token);
      navigate("/userGoals");
    } catch (error) {
      if (isAxiosError(error)) {
        toast.error(JSON.stringify(error.response?.data));
      }
    }
  };

  const handleLoginChange = (input: any) => {
    const { name, value } = input.target;
    setLoginCredentials((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <div className="background-image min-h-screen flex items-center justify-center">
      <div className="bg-white bg-opacity-80 p-8 rounded shadow-md">
        <h1 className="text-2xl mb-4">Login</h1>
        <input
          className="mb-4 p-2 border rounded w-full"
          type="text"
          placeholder="username or email"
          name="usernameOrEmail"
          onChange={handleLoginChange}
        />
        <input
          className="mb-4 p-2 border rounded w-full"
          type="password"
          placeholder="password"
          name="password"
          onChange={handleLoginChange}
        />
        <button
          className="bg-blue-500 text-white py-2 px-4 rounded w-full mb-2"
          onClick={login}
        >
          Login
        </button>
        <button
          className="bg-gray-500 text-white py-2 px-4 rounded w-full"
          onClick={() => navigate("/register")}
        >
          Register
        </button>
        <ToastContainer
          position="top-right"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
        />
      </div>
    </div>
  );
};
