import React, { Fragment } from 'react'
import NavigationPage from '../NavigationPage/NavigationPage'
import Contact from '../Contacts/Contact'

const ContactsPage = () => {
  return (
    
    <Fragment>
         <section className="container header">
        <h1>Contacts</h1>
    </section>
    <NavigationPage/>
    <Contact/>
    
   
    
        
    
  </Fragment>
  )
}

export default ContactsPage