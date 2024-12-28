import React, { useEffect, useState } from 'react'

const Contact = () => {
    const[users,setUsers] = useState([
        {
            name:"Stefan",
            email:"stefans@gmail.com",
            favorite:true
        },
        {
            name:"Ana",
            email:"annad@gmail.com",
            favorite:false
        },
        {
            name:"Tina",
            email:"tinag@gmail.com",
            favorite:false
        }
    ]
    )
  return (
    console.log(users)
    

    <section className="container contacts">
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>
                        <button>+ Add new</button>
                    </th>
                </tr>
                </thead>
                <tbody>
                { users.map(user => <User name={user.name} user={user} />) }
         
                <tr>
                <td>{user.name}</td>
                    {/* users.map((user)=>
                        <td>{user.name}</td>
                        <td>{user.email}</td>
                        <td>{user.favorite</td>
                    ); */}
                
                </tr>
                <tr>
                    <td>Stefan</td>
                    <td>stefan@vega.dev</td>
                    <td>
                        <button>Remove from favorites</button>
                    </td>
                </tr>
                <tr>
                  <td>Stefan</td>
                  <td>stefan@vega.dev</td>
                  <td>
                      <button>Add to favorites</button>
                  </td>
              </tr>
              <tr>
                  <td>Stefan</td>
                  <td>stefan@vega.dev</td>
                  <td>
                      <button>Remove from favorites</button>
                  </td>
              </tr>
                </tbody>
            </table>
        </section>
    
  )
}

export default Contact