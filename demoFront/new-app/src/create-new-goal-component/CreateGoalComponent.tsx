import { useEffect, useState } from "react"
import { GoalInterface } from "../interfaces/GoalInterface"
import axios from "axios"

export const CreateGoalCompnent: React.FC = () => {
    const [goal, setGoal] = useState<GoalInterface>({title: '', body: ''});
    const [goals, setGoals] = useState<GoalInterface[]>([]);

    const createGoal = async () => {
        await axios.post("http://localhost:8080/goals/newGoal", goal)
        getAllGoals();
    }

    const handleGoalChange = (input: any) => {
        const {name, value} = input.target;
        setGoal((prev) => ({...prev, [name]: value}))
    }

    useEffect(() => {getAllGoals()}, [])

    const getAllGoals = async () => {
        const response = await axios.get("http://localhost:8080/goals/allGoals")
        setGoals(response.data)
    }

    return(
        <div>
            <input name="title" type = "text" placeholder="Title" onChange={handleGoalChange}/>
            <input name="body" type = "text" placeholder="Body" onChange={handleGoalChange}/>
            <button onClick={createGoal}>Create Goal</button>
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