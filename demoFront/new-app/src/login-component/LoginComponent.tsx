import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

type Login = {usernameOrEmail: string, password: string}

export const LoginComponent:React.FC = () => {
    const navigate = useNavigate()
    const [loginCredentials, setLoginCredentials] = useState<Login>({usernameOrEmail: '', password: ''}) 

    const login = async () => {
        console.log(loginCredentials.usernameOrEmail)
        const response = await axios.post("http://localhost:8080/API/V1/auth/login", loginCredentials)
        console.log(response)
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
        </div>
    )

}