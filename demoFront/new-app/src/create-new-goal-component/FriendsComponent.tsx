import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { User } from "../interfaces/UserInterface";

export const FriendsComponent: React.FC<{
  friends: User[];
  getAllFriends: () => void;
}> = ({friends, getAllFriends}) => {
  const navigate = useNavigate();

  const unfriend = async(friendId: number) => {
    const token: any = localStorage.getItem("auth");
    await axios.delete(
      `http://localhost:8080/API/V1/users/user/unfriend/${friendId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    getAllFriends();
  }

  return (
    <div className="bg-[#E2E2B6] bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0">
      <h2 className="text-xl mb-4">Friends</h2>
      <ul className="list-none p-0">
        {friends.map((f) => (
          <li
            key={f.userId}
            className="mb-4 p-4 border border-gray-300 rounded bg-white shadow-sm"
          >
            <div className="mb-2">
              <strong>Username:</strong> {f.username}
            </div>
            <div>
              <strong>Number of public goals:</strong> {f.numberOfPublicGoals}
            </div>
            <div>
              <button
                onClick={() => navigate(`/friendProfile/${f.userId}`)}
                className="bg-[#6EACDA] text-white py-1 px-2 rounded mt-2 hover:bg-blue-700"
              >
                User Page
              </button>
            </div>
            <div>
              <button
                onClick={() => unfriend(f.userId)}
                className="bg-[#021526] text-white py-1 px-2 rounded mt-2 hover:bg-blue-700"
              >
                Unfriend
              </button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};