import React from 'react'
import NavigationPage from '../NavigationPage/NavigationPage'
import PersonalInfo from '../PersonalInfo/PersonalInfo'


const PersonalInfoPage = () => {
  return (
    <>
      <section className="container header">
         <h1>Contacts</h1>
      </section>
      <NavigationPage/>
   
      <PersonalInfo name="Stefan" lastname="Sumar" email="s.sumar@gmail.com"/>
        
  
    </>
  )
}

export default PersonalInfoPage