import React from 'react'

const PersonalInfo = (props) => {
  return (
    <section className="container details">
          <label>
            First Name:
            <input type="text" value={props.name} disabled />
          </label>
          <label>
            Last Name:
            <input type="text" value={props.lastname} disabled />
          </label>
          <label>
            Email:
            <input type="email" value={props.email} disabled/>
          </label>
        </section>
  )
}

export default PersonalInfo