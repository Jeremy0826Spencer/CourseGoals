import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { User } from "../interfaces/UserInterface";
import { Goal } from "../interfaces/GoalInterface";
import { Comment } from "../interfaces/CommentInterface";
import { CommentOnGoalModal } from "./CommentOnGoalModal";

interface ReviewModalProps {
  friendId: number;
}

export const FriendsGoalsComponent: React.FC<ReviewModalProps> = ({
  friendId
}) => {
  const [goals, setGoals] = useState<Goal[]>([]);
  const [profile, setProfile] = useState<User>();
  const [comments, setComments] = useState<{ [key: number]: Comment[] }>({});
  const [isCommentModalOpen, setIsCommentModalOpen] = useState<boolean>(false);
  const [selectedGoalId, setSelectedGoalId] = useState<number | null>(null);

  const navigate = useNavigate();

  const getFriendProfile = async () => {
    const token: any = localStorage.getItem("auth");
    const response = await axios.get(
      `http://localhost:8080/API/V1/users/user/${friendId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setProfile(response.data);
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

  const fetchGoals = async () => {
    const token: any = localStorage.getItem("auth");
    try {
      const response = await axios.get(
        `http://localhost:8080/API/V1/goals/user/${friendId}/friendGoals`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setGoals(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getFriendProfile();
    fetchGoals();
  }, [friendId]);

  useEffect(() => {
    goals.forEach((goal) => {
      getCommentsForGoal(goal.goalId);
    });
  }, [goals]);

  const openCommentModal = (goalId: number) => {
    setSelectedGoalId(goalId);
    setIsCommentModalOpen(true);
  };

  const closeCommentModal = () => {
    setIsCommentModalOpen(false);
    setSelectedGoalId(null);
    if (selectedGoalId) {
      getCommentsForGoal(selectedGoalId); // Refresh comments after adding a new one
    }
  };

  return (
    <div className="bg-[#03346E] min-h-screen flex flex-col items-center justify-center">
      <ul className="mb-4 md:mb-0 md:mr-4 bg-[#6EACDA] shadow-md rounded p-4 w-full md:w-auto space-y-4">
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">Username:</div>
          <div>{profile?.username}</div>
        </li>
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">Number of goals:</div>
          <div>{profile?.numberOfPublicGoals}</div>
        </li>
      </ul>
      <div className="bg-white bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0">
        <h2 className="text-xl mb-4">Goals</h2>
        <ul className="list-none p-0">
          {goals.map((goal) => (
            <li
              key={goal.goalId}
              className="mb-4 p-4 border border-gray-300 rounded bg-white shadow-sm"
            >
              <div className="mb-2">
                <strong>Title:</strong> {goal.title}
              </div>
              <div>
                <strong>Body:</strong> {goal.body}
              </div>
              <button
                onClick={() => openCommentModal(goal.goalId)}
                className="mt-2 px-4 py-2 bg-[#6EACDA] text-white rounded hover:bg-blue-600 transition-colors"
              >
                Add Comment
              </button>
              <ul className="list-none p-0 mt-4">
                {comments[goal.goalId]?.map((comment) => (
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
        <button
          className="mt-2 px-4 py-2 bg-[#6EACDA] text-white rounded hover:bg-blue-700"
          onClick={() => {
            navigate("/userGoals");
          }}
        >
          Home
        </button>
      </div>
      {isCommentModalOpen && selectedGoalId && (
        <CommentOnGoalModal
          isOpen={isCommentModalOpen}
          onClose={closeCommentModal}
          goalId={selectedGoalId}
        />
      )}
    </div>
  );
};
