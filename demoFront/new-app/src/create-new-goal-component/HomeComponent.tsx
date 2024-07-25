
import React from "react";
import { ToastContainer} from "react-toastify";
import { FriendsComponent } from "./FriendsComponent";
import { PeopleYouMayKnowComponent } from "./PeopleYouMayKnowComponent";
import { GoalsComponent } from "./GoalsComponent";
import { CreateGoalFormComponent } from "./CreateGoalFormComponent";

export const HomeComponent: React.FC = () => {
  
  return (
    <div>
      <div className="background-image min-h-screen flex flex-col md:flex-row items-start justify-center bg-gray-100 p-4">
        <CreateGoalFormComponent />
        <GoalsComponent />
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
