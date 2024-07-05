import axios, { isAxiosError } from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

type Register = {
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
};

export const RegisterComponent: React.FC = () => {
  const navigate = useNavigate();
  const [registerCredentials, setRegisterCredentials] = useState<Register>({
    username: "",
    password: "",
    firstName: "",
    lastName: "",
    email: "",
  });

  const handleRegisterChange = (input: any) => {
    const { name, value } = input.target;
    setRegisterCredentials((prev) => ({ ...prev, [name]: value }));
  };

  const register = async () => {
    try {
      await axios.post(
        "http://localhost:8080/API/V1/auth/register",
        registerCredentials
      );
    } catch (error) {
      if (isAxiosError(error)) {
        toast.error(JSON.stringify(error.response?.data));
      }
    }
  };

  return (
    <div className=" background-image min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded shadow-md w-full max-w-md">
        <h1 className="text-2xl mb-6 text-center">Register</h1>
        <input
          type="text"
          placeholder="username"
          name="username"
          onChange={handleRegisterChange}
          className="mb-4 p-2 border rounded w-full"
        />
        <input
          type="password"
          placeholder="password"
          name="password"
          onChange={handleRegisterChange}
          className="mb-4 p-2 border rounded w-full"
        />
        <input
          type="text"
          placeholder="first name"
          name="firstName"
          onChange={handleRegisterChange}
          className="mb-4 p-2 border rounded w-full"
        />
        <input
          type="text"
          placeholder="last name"
          name="lastName"
          onChange={handleRegisterChange}
          className="mb-4 p-2 border rounded w-full"
        />
        <input
          type="text"
          placeholder="email"
          name="email"
          onChange={handleRegisterChange}
          className="mb-4 p-2 border rounded w-full"
        />
        <button
          onClick={register}
          className="bg-blue-500 text-white py-2 px-4 rounded w-full mb-2 hover:bg-blue-700"
        >
          Register
        </button>
        <button
          onClick={() => navigate("/")}
          className="bg-gray-500 text-white py-2 px-4 rounded w-full hover:bg-gray-700"
        >
          Back To Login
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
