import { useEffect, useState } from "react";
import axios, { isAxiosError } from "axios";
import React from "react";
import Select from "react-select";
import { ToastContainer, toast } from "react-toastify";

type Goal = {
  goalId: number;
  title: string;
  body: string;
  privacyEnum: string;
};

export const CreateGoalCompnent: React.FC = () => {
  const [goal, setGoal] = useState<Goal>({
    goalId: 0,
    title: "",
    body: "",
    privacyEnum: "",
  });
  const [goals, setGoals] = useState<Goal[]>([]);
  const [status, setStatus] = useState<string>("PUBLIC");

  const options = [
    { value: "PUBLIC", label: "PUBLIC" },
    { value: "PRIVATE", label: "PRIVATE" },
  ];

  const createGoal = async () => {
    const token: any = localStorage.getItem("auth");
    goal.privacyEnum = status;
    try {
      await axios.post("http://localhost:8080/API/V1/goals/user/goal", goal, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      getAllGoalsForUser();
    } catch (error) {
      if (isAxiosError(error)) {
        toast.error(JSON.stringify(error.response?.data));
      }
    }
  };

  const deleteGoal = async (goalId?: number) => {
    const token: any = localStorage.getItem("auth");
    console.log(goalId);
    try {
      await axios.delete(`http://localhost:8080/API/V1/goals/user/${goalId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      getAllGoalsForUser();
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

  useEffect(() => {
    getAllGoalsForUser();
  }, []);

  const getAllGoalsForUser = async () => {
    const token: any = localStorage.getItem("auth");
    const response = await axios.get(
      "http://localhost:8080/API/V1/goals/user/getUserGoals",
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setGoals(response.data);
  };

  return (
    <div className="background-image min-h-screen flex flex-col md:flex-row items-start justify-center bg-gray-100 p-4">
      <div className="bg-white p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0">
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
            setStatus(selectedOption ? selectedOption.value : "")
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
      <div className="bg-white p-6 rounded shadow-md w-full max-w-md">
        <ul className="list-none p-0">
          {goals.map((g) => (
            <li
              key={g.goalId}
              className="mb-4 p-4 border border-gray-300 rounded bg-white shadow-sm"
            >
              <div className="mb-2">
                <strong>Title:</strong> {g.title}
              </div>
              <div>
                <strong>Body:</strong> {g.body}
              </div>
              <div>
                <button
                  className="bg-emerald-950 text-white rounded"
                  onClick={() => deleteGoal(g.goalId)}
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
      />
    </div>
  );
};
