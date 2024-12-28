import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const HomePage = () => {
  const navigate = useNavigate();
  const [count,setCounter]=useState(0);
  
  return (
    

    <div id="root">
      <img alt="logo" src="./assets/images/VegaItnovi.png"/>
      <button className="go_button" onClick={()=>navigate("/contacts")}>Go to the app</button>
      <button className="dugme" onClick={()=>setCounter(count+1)}>{count}</button>
    </div>
  )
}

export default HomePage