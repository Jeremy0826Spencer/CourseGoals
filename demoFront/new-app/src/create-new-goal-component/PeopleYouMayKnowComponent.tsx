import { useEffect, useState } from "react";
import { User } from "../interfaces/UserInterface"
import axios from "axios";


export const PeopleYouMayKnowComponent: React.FC<{onFriendAdded: () => void}> = ({
  onFriendAdded,
}) => {
    const [accounts, setAccounts] = useState<User[]>([]);

    const friend = async (userId: number) => {
      const token: any = localStorage.getItem("auth");
      await axios.patch(
        `http://localhost:8080/API/V1/users/user/${userId}/friend`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      onFriendAdded();
    };

    const getAllUsers = async () => {
      const token: any = localStorage.getItem("auth");
      const response = await axios.get(
        "http://localhost:8080/API/V1/users/user/users",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setAccounts(response.data);
    };

    useEffect(() => {
      getAllUsers();
    }, []);

    return (
      <div>
        <div
          className="bg-white bg-cover bg-center p-6 rounded shadow-md w-full max-w-md md:mr-8 mb-8 md:mb-0"
          style={{ backgroundImage: "url('/books.jpg')" }}
        >
          <h2 className="text-xl mb-4">People you may know</h2>
          <ul className="list-none p-0">
            {accounts.map((a) => (
              <li
                key={a.userId}
                className="mb-4 p-4 border border-gray-300 rounded bg-white shadow-sm"
              >
                <div className="mb-2">
                  <strong>Username:</strong> {a.username}
                </div>
                <div>
                  <strong>Number of public goals:</strong>{" "}
                  {a.numberOfPublicGoals}
                </div>
                <div>
                  <button
                    className="bg-green-500 text-white py-1 px-2 rounded mt-2 hover:bg-green-700"
                    onClick={() => friend(a.userId)}
                  >
                    Friend
                  </button>
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    );

}