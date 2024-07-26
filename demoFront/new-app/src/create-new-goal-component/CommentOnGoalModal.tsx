import axios from "axios";
import { useEffect, useState } from "react";
import { Comment } from "../interfaces/CommentInterface";

interface ReviewModalProps {
  isOpen: boolean;
  onClose: () => void;
  goalId: number;
}



export const CommentOnGoalModal: React.FC<ReviewModalProps> = ({
  isOpen,
  onClose,
  goalId
}) => {
  const [commentText, setCommentText] = useState<string>("");

  if (!isOpen) return null;

  const submitComment = async () => {
    const token: any = localStorage.getItem("auth");
    const newComment: Partial<Comment> = {commentText, goalId};
    try {
      await axios.post(
        `http://localhost:8080/API/V1/comments/user/comment`,
        newComment,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="modal fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="modal-content bg-white p-6 rounded shadow-lg w-11/12 md:w-1/3">
        <h2 className="text-2xl font-bold mb-4">Edit Profile</h2>
        <input
          type="text"
          placeholder="Comment text"
          name="commentText"
          value={commentText}
          onChange={(e) => setCommentText(e.target.value)}
          className="w-full mb-4 p-2 border border-gray-300 rounded"
        />
        <div className="flex justify-end space-x-4">
          <button
            onClick={submitComment}
            className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition-colors"
          >
            submit
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
