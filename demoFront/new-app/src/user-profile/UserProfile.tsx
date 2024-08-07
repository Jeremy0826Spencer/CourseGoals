import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { EditProfileModal } from "./EditProfileModal";

type Profile = {
  username: string;
  firstName: string;
  lastName: string;
  email: string;
};

export const UserProfile: React.FC = () => {
  const [profile, setProfile] = useState<Profile>({
    username: "",
    firstName: "",
    lastName: "",
    email: "",
  });
  const [isEditProfileModalOpen, setIsEditProfileModalOpen] = useState(false);
  const navigate = useNavigate();

  const openModal = () => {
    setIsEditProfileModalOpen(true);
  };
  const closeModal = () => {
    setIsEditProfileModalOpen(false);
  };

  const getMyProfile = async () => {
    const token: any = localStorage.getItem("auth");
    const response = await axios.get(
      "http://localhost:8080/API/V1/users/user/myAccount",
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setProfile(response.data);
  };

  const deleteProfile = async () => {
    const token: any = localStorage.getItem("auth");
    await axios.delete("http://localhost:8080/API/V1/users/user/myAccount", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    navigate("/");
  };

  useEffect(() => {
    getMyProfile();
  }, []);

  return (
    <div className="bg-[#03346E] min-h-screen flex flex-col md:flex-row items-start justify-center p-4">
      <ul className="mb-4 md:mb-0 md:mr-4 bg-[#E2E2B6] shadow-md rounded p-4 w-full md:w-auto space-y-4">
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">Username:</div>
          <div>{profile?.username}</div>
        </li>
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">First Name:</div>
          <div>{profile?.firstName}</div>
        </li>
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">Last Name:</div>
          <div>{profile?.lastName}</div>
        </li>
        <li className="flex flex-col md:flex-row md:items-center">
          <div className="font-semibold md:mr-2">Email:</div>
          <div>{profile?.email}</div>
        </li>
      </ul>
      <div className="flex flex-col space-y-2 w-full md:w-auto">
        <EditProfileModal
          isOpen={isEditProfileModalOpen}
          onClose={closeModal}
          profileData={profile}
          resetProfile={getMyProfile}
        />
        <button
          onClick={openModal}
          className="px-4 py-2 bg-[#6EACDA] text-white rounded hover:bg-blue-600 transition-colors"
        >
          Edit Profile
        </button>
        <button
          onClick={deleteProfile}
          className="px-4 py-2 bg-[#021526] text-white rounded hover:bg-red-600 transition-colors"
        >
          Delete Profile
        </button>
      </div>
    </div>
  );
};
