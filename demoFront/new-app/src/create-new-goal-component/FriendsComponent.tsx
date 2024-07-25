import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { User } from "../interfaces/UserInterface";

export const FriendsComponent: React.FC = () => {
    const navigate = useNavigate();
    const [friends, setFriends] = useState<User[]>([]);

    const getAllFriends = async () => {
      const token: any = localStorage.getItem("auth");
      const response = await axios.get(
        "http://localhost:8080/API/V1/users/user/friends",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setFriends(response.data);
    };

    useEffect(() => {
      getAllFriends();
    }, []);

    return (
      <div>
        <div
          className="bg-white bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0"
          style={{ backgroundImage: "url('/books.jpg')" }}
        >
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
                  <strong>Number of public goals:</strong>{" "}
                  {f.numberOfPublicGoals}
                </div>
                <div>
                  <button
                    onClick={() => navigate(`/friendProfile/${f.userId}`)}
                    className="bg-blue-500 text-white py-1 px-2 rounded mt-2 hover:bg-blue-700"
                  >
                    User Page
                  </button>
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    );


}