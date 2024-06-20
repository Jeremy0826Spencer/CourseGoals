import axios, { isAxiosError } from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


type Login = {usernameOrEmail?: string, password?: string}

export const LoginComponent:React.FC = () => {
    const navigate = useNavigate()
    const [loginCredentials, setLoginCredentials] = useState<Login>({}) 

    const login = async () => {
        try{
            const response = await axios.post("http://localhost:8080/API/V1/auth/login", loginCredentials)
            const token = response.data.accessToken
            localStorage.setItem("auth", token)
            navigate('/userGoals')
        } catch (error) {
            if (isAxiosError(error)) {
                toast.error(JSON.stringify(error.response?.data));
            }
        }           
    }

    const handleLoginChange = (input: any) => {
        const {name, value} = input.target;
        setLoginCredentials((prev) => ({...prev, [name]: value}))
    }

    return(
        <div>
            
            <h1>Login</h1>
            <input type="text" placeholder="username or email" name="usernameOrEmail" onChange={handleLoginChange}/>
            <input type="password" placeholder="password" name="password" onChange={handleLoginChange}/>
            <button onClick={login}>Login</button>
            <button onClick={() => navigate('/register')}>Register</button> 
            <ToastContainer position="top-right" autoClose={5000} hideProgressBar={false} newestOnTop={false} closeOnClick rtl={false} />             
        </div>
    )

}