import { useEffect, useState } from "react"
import { GoalInterface } from "../interfaces/GoalInterface"
import axios, { isAxiosError } from "axios"
import React from "react"
import Select from "react-select";
import { ToastContainer, toast } from "react-toastify";

export const CreateGoalCompnent: React.FC = () => {
    const [goal, setGoal] = useState<GoalInterface>({title: '', body: '', privacyEnum: ''});
    const [goals, setGoals] = useState<GoalInterface[]>([]);
    const [status, setStatus] = useState<string>('PUBLIC'); 

    const options = [
        { value: 'PUBLIC', label: 'PUBLIC' },
        { value: 'PRIVATE', label: 'PRIVATE' }
    ]

    const createGoal = async () => {
        const token: any = localStorage.getItem('auth')
        goal.privacyEnum = status;
        try{
            await axios.post("http://localhost:8080/API/V1/goals/user/goal", goal, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
            getAllGoalsForUser();
        }catch (error) {
            if (isAxiosError(error)) {
                toast.error(JSON.stringify(error.response?.data));
            }
        } 
    }

    const handleGoalChange = (input: any) => {
        const {name, value} = input.target;
        setGoal((prev) => ({...prev, [name]: value}))
    }

    useEffect(() => {getAllGoalsForUser()}, [])

    const getAllGoalsForUser = async () => {
        const token: any = localStorage.getItem('auth')
        const response = await axios.get("http://localhost:8080/API/V1/goals/user/getUserGoals", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
        setGoals(response.data)
    }

    return(
        <div>
            <input name="title" type = "text" placeholder="Title" onChange={handleGoalChange}/>
            <input name="body" type = "text" placeholder="Body" onChange={handleGoalChange}/>
            <Select
                options={options}
                name="privacyEnum"
                defaultValue={options[0]}
                value={options.find((opt) => opt.value === status)}
                onChange={(selectedOption) => setStatus(selectedOption ? selectedOption.value : '')}
            />
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
            <ToastContainer position="top-right" autoClose={5000} hideProgressBar={false} newestOnTop={false} closeOnClick rtl={false} />    
        </div>
    )
}