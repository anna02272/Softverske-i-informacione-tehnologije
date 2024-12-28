import React from 'react'
import { useNavigate } from 'react-router-dom'

const NavigationPage = () => {

  
  const navigate = useNavigate();

 // const naziv = (path) => navigate(path)
  return (
    <section className="container global">
        <section className="container navigation">
            <button onClick={()=>navigate("/personal")}>Personal Info</button>
            <button className="active" onClick={()=>navigate("/contacts")}>Contacts</button>
        </section>
        </section>
       
  )
}

export default NavigationPage