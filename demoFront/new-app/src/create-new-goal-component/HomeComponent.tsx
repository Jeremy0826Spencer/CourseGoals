
import React, { useEffect, useState } from "react";
import { ToastContainer} from "react-toastify";
import { FriendsComponent } from "./FriendsComponent";
import { PeopleYouMayKnowComponent } from "./PeopleYouMayKnowComponent";
import { GoalsComponent } from "./GoalsComponent";
import { CreateGoalFormComponent } from "./CreateGoalFormComponent";
import axios from "axios";
import { User } from "../interfaces/UserInterface";

export const HomeComponent: React.FC = () => {
  const [goals, setGoals] = useState([]);
  const [friends, setFriends] = useState<User[]>([]);

  const fetchAllGoalsForUser = async () => {
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

  const fetchAllFriends = async () => {
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
    fetchAllGoalsForUser();
    fetchAllFriends();
  }, []);
  
  return (
    <div className="bg-[#03346E] min-h-screen flex flex-col lg:flex-row items-start justify-center p-4">
      <div className="w-full lg:w-2/3 flex flex-col items-start p-4">
        <CreateGoalFormComponent onGoalCreated={fetchAllGoalsForUser} />
        <GoalsComponent
          goals={goals}
          getAllGoalsForUser={fetchAllGoalsForUser}
        />
      </div>
      <div className="w-full lg:w-1/3 flex flex-col items-start p-4">
        <PeopleYouMayKnowComponent onFriendAdded={fetchAllFriends} />
        <FriendsComponent friends={friends} getAllFriends={fetchAllFriends} />
      </div>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
      />
    </div>
  );
}
