import axios from "axios";
import { useEffect, useState } from "react";

type Goal = {
  goalId: number;
  title: string;
  body: string;
  privacyEnum: string;
};
type Account = {
    userId: number;
    username: string;
    accountLocked: boolean;
}
export const AdminPage: React.FC = () => {
    const [goals, setGoals] = useState<Goal[]>([])
    const [accounts, setAccounts] = useState<Account[]>([])

    const getAllGoals = async () => {
        const token: any = localStorage.getItem('auth')
        const response = await axios.get(
          "http://localhost:8080/API/V1/goals/admin/allGoals",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setGoals(response.data)
    }

    const getAllUsers = async () => {
      const token: any = localStorage.getItem("auth");
      const response = await axios.get(
        "http://localhost:8080/API/V1/users/admin/accounts",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setAccounts(response.data);
    };

    const toggleLockAccount = async (userId: number) => {
      const token: any = localStorage.getItem("auth");
      await axios.patch(
        `http://localhost:8080/API/V1/users/admin/${userId}/toggle`,{},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      getAllUsers()
    };

    useEffect(() => {
        getAllGoals()
        getAllUsers()
    },[])

    return (
      <div className="p-4 bg-gray-100 min-h-screen">
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-center text-blue-600">
            Admin
          </h1>
        </div>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          <ul className="bg-white p-4 rounded-lg shadow-md">
            <h2 className="text-2xl font-semibold mb-4">Goals</h2>
            {goals.map((g) => (
              <li key={g.goalId} className="border-b border-gray-200 py-2">
                <span className="block text-gray-600 font-medium">
                  {g.goalId}
                </span>
                <span className="block text-gray-900">{g.title}</span>
                <span className="block text-gray-700">{g.body}</span>
              </li>
            ))}
          </ul>
          <ul className="bg-white p-4 rounded-lg shadow-md">
            <h2 className="text-2xl font-semibold mb-4">Accounts</h2>
            {accounts.map((a) => (
              <li key={a.userId} className="border-b border-gray-200 py-2">
                <span className="block text-gray-600 font-medium">
                  {a.userId}
                </span>
                <span className="block text-gray-900">{a.username}</span>
                <span className="block text-gray-900">
                  {a.accountLocked ? "Locked" : "Unlocked"}
                </span>
                <button onClick={() => toggleLockAccount(a.userId)}>
                  Toggle Account
                </button>
              </li>
            ))}
          </ul>
        </div>
      </div>
    );

}