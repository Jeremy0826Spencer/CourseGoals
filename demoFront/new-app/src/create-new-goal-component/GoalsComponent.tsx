import { Goal } from "../interfaces/GoalInterface";
import { Comment } from "../interfaces/CommentInterface";
import axios, { isAxiosError } from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";

export const GoalsComponent: React.FC<{
  goals: Goal[];
  getAllGoalsForUser: () => void
}> = ({ goals, getAllGoalsForUser }) => {

  const [comments, setComments] = useState<{ [key: number]: Comment[] }>({});

  const deleteGoal = async (goalId?: number) => {
    const token: any = localStorage.getItem("auth");
    try {
      await axios.delete(`http://localhost:8080/API/V1/goals/user/${goalId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Goal deleted successfully!");
      getAllGoalsForUser();
    } catch (error) {
      if (isAxiosError(error)) {
        toast.error(JSON.stringify(error.response?.data));
      }
    }
  };

  const getCommentsForGoal = async (goalId: number) => {
    const token: any = localStorage.getItem("auth");
    const response = await axios.get(
      `http://localhost:8080/API/V1/comments/user/comments/${goalId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setComments((prevComments) => ({
      ...prevComments,
      [goalId]: response.data,
    }));
  };

  useEffect(() => {
    goals.forEach((goal) => {
      getCommentsForGoal(goal.goalId);
    });
  }, [goals]);

  return (
    <div className="bg-[#E2E2B6] bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0">
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
                className="bg-[#021526] text-white py-1 px-2 rounded mt-2 hover:bg-red-700"
                onClick={() => deleteGoal(g.goalId)}
              >
                Delete
              </button>
            </div>
            <ul className="list-none p-0 mt-4">
              {comments[g.goalId]?.map((comment) => (
                <li
                  key={comment.commentId}
                  className="mb-4 p-4 border border-gray-300 rounded bg-white shadow-sm"
                >
                  <div>
                    <strong>Comment:</strong> {comment.commentText}
                  </div>
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
};