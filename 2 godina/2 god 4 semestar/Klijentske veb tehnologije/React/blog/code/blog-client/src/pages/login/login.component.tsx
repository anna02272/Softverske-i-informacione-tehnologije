import React, { FormEvent } from 'react';

export default class Login extends React.Component<{onLogin: (username:string, password:string)=>void}, {username:string, password:string}> {
    constructor(props:any){
        super(props);
        this.state = {
            username:'',
            password:''
        }
    }
    
    handleSubmit = (event:FormEvent<HTMLFormElement>) => {
        this.props.onLogin(this.state.username, this.state.password);
        event.preventDefault();
    }

    handleChange = (event:{target: {name: any; value:any}}) => {
        const newState = { [event.target.name]: event.target.value } as Pick<{username:string; password:string}, keyof {username: string; password: string}>;
        this.setState(newState);
    }

    render(){
        return (
        <div className="container">
            <div className="row">
                <div className="col-md-3"></div>
                <div className="col-md-6">
                <form className="form-signin" onSubmit={this.handleSubmit}>
                    <h2 className="form-signin-heading">Please sign in</h2>
                    <label htmlFor="username" className="sr-only">Username</label>
                    <input type="text" id="username" className="form-control" name="username" placeholder="Username"
                    required value={this.state.username} onChange={this.handleChange}/>
                    <label htmlFor="inputPassword" className="sr-only">Password</label>
                    <input type="password" id="inputPassword" className="form-control" name="password" placeholder="Password" required value={this.state.password} onChange={this.handleChange}/>
                    <button className="btn btn-primary btn-block" type="submit">Sign in</button>
                </form>
                </div>
                <div className="col-md-3"></div>
            </div>
        </div> 
        )
    }
}