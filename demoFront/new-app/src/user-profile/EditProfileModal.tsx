import axios from "axios";
import { useEffect, useState } from "react";

interface ReviewModalProps {
  isOpen: boolean;
  onClose: () => void;
  children?: React.ReactNode;
  profileData: Profile;
  resetProfile: () => any;
}

type Profile = { firstName: string; lastName: string; email: string };

export const EditProfileModal: React.FC<ReviewModalProps> = ({
  isOpen,
  onClose,
  profileData,
  resetProfile,
}) => {
  const [profile, setProfile] = useState<Profile>(profileData);

  useEffect(() => {
    setProfile(profileData);
  }, [profileData]);

  if (!isOpen) return null;

  const submitProfileChange = async () => {
    const token: any = localStorage.getItem("auth");
    try {
      await axios.put(
        "http://localhost:8080/API/V1/users/user/myAccount",
        profile,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      alert("Profile updated successfully");
      resetProfile();
      onClose();
    } catch (error) {
      console.error(error);
    }
  };

  const storeValues = (input: any) => {
    const { name, value } = input.target;
    setProfile((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <div className="modal fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="modal-content bg-white p-6 rounded shadow-lg w-11/12 md:w-1/3">
        <h2 className="text-2xl font-bold mb-4">Edit Profile</h2>
        <input
          type="text"
          placeholder="First Name"
          name="firstName"
          value={profile.firstName}
          onChange={storeValues}
          className="w-full mb-4 p-2 border border-gray-300 rounded"
        />
        <input
          type="text"
          placeholder="Last Name"
          name="lastName"
          value={profile.lastName}
          onChange={storeValues}
          className="w-full mb-4 p-2 border border-gray-300 rounded"
        />
        <input
          type="text"
          placeholder="Email"
          name="email"
          value={profile.email}
          onChange={storeValues}
          className="w-full mb-4 p-2 border border-gray-300 rounded"
        />
        <div className="flex justify-end space-x-4">
          <button
            onClick={submitProfileChange}
            className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition-colors"
          >
            Submit Profile Change
          </button>
          <button
            onClick={onClose}
            className="px-4 py-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400 transition-colors"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};
