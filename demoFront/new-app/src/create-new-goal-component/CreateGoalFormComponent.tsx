import axios, { isAxiosError } from "axios";
import { toast } from "react-toastify";
import { Goal } from "../interfaces/GoalInterface";
import { useState } from "react";
import Select from "react-select";
import { useNavigate } from "react-router-dom";

export const CreateGoalFormComponent: React.FC = () => {
  const [status, setStatus] = useState<string>("PUBLIC");
  const [goal, setGoal] = useState<Goal>({
    goalId: 0,
    title: "",
    body: "",
    privacyEnum: "PUBLIC",
  });

  const options = [
    { value: "PUBLIC", label: "PUBLIC" },
    { value: "PRIVATE", label: "PRIVATE" },
  ];

  const createGoal = async () => {
    const token: any = localStorage.getItem("auth");
    try {
      await axios.post("http://localhost:8080/API/V1/goals/user/goal", goal, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    } catch (error) {
      if (isAxiosError(error)) {
        toast.error(JSON.stringify(error.response?.data));
      }
    }
  };

  const handleGoalChange = (input: any) => {
    const { name, value } = input.target;
    setGoal((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <div>
      <div className="flex flex-col space-y-2 w-full md:w-auto">
        <div
          className="bg-white bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0"
          style={{ backgroundImage: "url('/books.jpg')" }}
        >
          <h1 className="text-2xl mb-4 text-center">Create Goal</h1>
          <input
            name="title"
            type="text"
            placeholder="Title"
            onChange={handleGoalChange}
            className="mb-4 p-2 border rounded w-full"
          />
          <input
            name="body"
            type="text"
            placeholder="Body"
            onChange={handleGoalChange}
            className="mb-4 p-2 border rounded w-full"
          />
          <Select
            options={options}
            name="privacyEnum"
            defaultValue={options[0]}
            value={options.find((opt) => opt.value === status)}
            onChange={(selectedOption) =>
              setStatus(selectedOption ? selectedOption.value : "PUBLIC")
            }
            className="mb-4"
          />
          <button
            onClick={createGoal}
            className="bg-blue-500 text-white py-2 px-4 rounded w-full mb-4 hover:bg-blue-700"
          >
            Create Goal
          </button>
        </div>
      </div>
    </div>
  );
};
