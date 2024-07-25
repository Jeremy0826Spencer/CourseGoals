import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

interface ReviewModalProps {
  friendId: number;
}

type User = {
  userId: number;
  username: string;
  numberOfPublicGoals: number;
};

type Goal = {
  goalId: number;
  title: string;
  body: string;
};

export const FriendsGoalsComponent: React.FC<ReviewModalProps> = ({
  friendId
}) => {
  const [goals, setGoals] = useState<Goal[]>([]);
  const [profile, setProfile] = useState<User>();

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

  return (
    <div>
      <ul className="mb-4 md:mb-0 md:mr-4 bg-white shadow-md rounded p-4 w-full md:w-auto space-y-4">
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">Username:</div>
          <div>{profile?.username}</div>
        </li>
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">Number of goals:</div>
          <div>{profile?.numberOfPublicGoals}</div>
        </li>
      </ul>
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
            </li>
          ))}
        </ul>
        <button
          onClick={() => {
            navigate("/userGoals");
          }}
        >
          Home
        </button>
      </div>
    </div>
  );
};
