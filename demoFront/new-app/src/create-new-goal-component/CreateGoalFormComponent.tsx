import axios, { isAxiosError } from "axios";
import { toast } from "react-toastify";
import { Goal } from "../interfaces/GoalInterface";
import { useState } from "react";
import Select from "react-select";

export const CreateGoalFormComponent: React.FC<{ onGoalCreated: () => void }> = ({
  onGoalCreated,
}) => {
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
      toast.success("Goal created successfully!");
      onGoalCreated();
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
        <div className="bg-[#E2E2B6] bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0">
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
            className="bg-[#6EACDA] text-white py-2 px-4 rounded w-full mb-4 hover:bg-blue-700"
          >
            Create Goal
          </button>
        </div>
      </div>
    </div>
  );
};
