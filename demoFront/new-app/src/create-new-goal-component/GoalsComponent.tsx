import { useEffect, useState } from "react";
import { Goal } from "../interfaces/GoalInterface";
import axios, { isAxiosError } from "axios";
import { toast } from "react-toastify";

export const GoalsComponent:React.FC = () => {
    const [goals, setGoals] = useState<Goal[]>([]);

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

    const deleteGoal = async (goalId?: number) => {
      const token: any = localStorage.getItem("auth");
      console.log(goalId);
      try {
        await axios.delete(
          `http://localhost:8080/API/V1/goals/user/${goalId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        getAllGoalsForUser();
      } catch (error) {
        if (isAxiosError(error)) {
          toast.error(JSON.stringify(error.response?.data));
        }
      }
    };

    useEffect(() => {
      getAllGoalsForUser();
    }, []);

    return(
        <div
        className="bg-white bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0"
        style={{ backgroundImage: "url('/books.jpg')" }}
        >
            <h2 className="text-xl mb-4">Goals</h2>
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
                        className="bg-red-500 text-white py-1 px-2 rounded mt-2 hover:bg-red-700"
                        onClick={() => deleteGoal(g.goalId)}
                    >
                        Delete
                    </button>
                    </div>
                </li>
                ))}
            </ul>
        </div>
    )
}