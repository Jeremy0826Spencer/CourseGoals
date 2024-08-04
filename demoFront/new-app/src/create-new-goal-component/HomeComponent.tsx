
import React, { useEffect, useState } from "react";
import { ToastContainer} from "react-toastify";
import { FriendsComponent } from "./FriendsComponent";
import { PeopleYouMayKnowComponent } from "./PeopleYouMayKnowComponent";
import { GoalsComponent } from "./GoalsComponent";
import { CreateGoalFormComponent } from "./CreateGoalFormComponent";
import axios from "axios";

export const HomeComponent: React.FC = () => {
  const [goals, setGoals] = useState([]);

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

  useEffect(() => {
    getAllGoalsForUser();
  }, []);
  
  return (
    <div>
      <div className="background-image min-h-screen flex flex-col md:flex-row items-start justify-center bg-gray-100 p-4">
        <CreateGoalFormComponent onGoalCreated={getAllGoalsForUser}/>
        <GoalsComponent goals={goals} getAllGoalsForUser={getAllGoalsForUser}/>
        <PeopleYouMayKnowComponent />
        <FriendsComponent />
        <ToastContainer
          position="top-right"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
        />
      </div>
    </div>
  );
}
