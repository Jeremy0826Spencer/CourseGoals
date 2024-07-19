import axios from "axios";
import { useEffect, useState } from "react";

type Goal = {
  goalId: number;
  title: string;
  body: string;
  privacyEnum: string;
};
export const AdminPage: React.FC = () => {
    const [goals, setGoals] = useState<Goal[]>([]);

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

    useEffect(() => {
        getAllGoals()
    })

    return(

        <div>
            <div>
                <h1>Admin</h1>

            </div>
            <ul>
                {goals.map((g) => (
                    <li key={g.goalId}>
                        {g.goalId}
                        {g.title}
                        {g.body}
                    </li>
                ))}
            </ul> 

        </div>
    )

}