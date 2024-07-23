import axios from "axios";
import { useEffect, useState } from "react";

interface ReviewModalProps {
  isOpen: boolean;
  onClose: () => void;
  children?: React.ReactNode;
  friendId: number;
}

type Goal = {
  goalId: number;
  title: string;
  body: string;
};

export const FriendsGoalsComponent: React.FC<ReviewModalProps> = ({
  isOpen,
  onClose,
  friendId
}) => {
  const [goals, setGoals] = useState<Goal[]>([]);

  useEffect(() => {
    if (isOpen) {
      const fetchGoals = async () => {
        const token: any = localStorage.getItem("auth");
        try {
          const response = await axios.get(
            `http://localhost:8080/api/v1/users/${friendId}/goals`,
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
      fetchGoals();
    }
  }, [isOpen, friendId]);

  return (
    <div className="modal fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
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
        <button onClick={() => {onClose()}}>Close</button>
      </div>
    </div>
  );
};
