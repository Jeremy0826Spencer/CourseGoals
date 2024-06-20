import axios, { isAxiosError } from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { ToastContainer, toast } from "react-toastify";

type Register = {username: string, password: string, firstName: string, lastName: string, email: string}

export const RegisterComponent:React.FC = () => {
    const navigate = useNavigate()
    const [registerCredentials, setRegisterCredentials] = useState<Register>({username: '', password: '', firstName: '', lastName: '', email: ''})

    const handleRegisterChange = (input: any) => {
        const {name, value} = input.target;
        setRegisterCredentials((prev) => ({...prev, [name]: value}))
    }

    const register = async () => {
        try{
            await axios.post("http://localhost:8080/API/V1/auth/register", registerCredentials)
        }catch(error) {
            if(isAxiosError(error)) {
                toast.error(JSON.stringify(error.response?.data));
            }
        } 
    }

    return(
        <div>
            <input type="text" placeholder="username" name="username" onChange={handleRegisterChange}/>
            <input type="password" placeholder="password" name="password" onChange={handleRegisterChange}/>
            <input type="text" placeholder="first name" name="firstName" onChange={handleRegisterChange}/>
            <input type="text" placeholder="last name" name="lastName" onChange={handleRegisterChange}/>
            <input type="text" placeholder="email" name="email" onChange={handleRegisterChange}/>
            <button onClick={register}>Register</button>
            <button onClick={() => navigate('/')}>Back To Login</button>
            <ToastContainer position="top-right" autoClose={5000} hideProgressBar={false} newestOnTop={false} closeOnClick rtl={false} /> 
        </div>
    )


}